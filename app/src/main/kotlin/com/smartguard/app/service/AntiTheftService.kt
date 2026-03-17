package com.smartguard.app.service

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.smartguard.app.database.Incident
import com.smartguard.app.database.IncidentDatabase
import com.smartguard.app.repository.CameraRepository
import com.smartguard.app.repository.LocationRepository
import com.smartguard.app.utils.AlarmPlayer
import com.smartguard.app.utils.ConnectivityHelper
import com.smartguard.app.utils.EmailSender
import com.smartguard.app.utils.NotificationHelper
import com.smartguard.app.utils.PreferencesManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AntiTheftService : LifecycleService() {
    companion object {
        private const val TAG = "AntiTheftService"
        var isRunning = false
    }

    private lateinit var locationRepository: LocationRepository
    private lateinit var cameraRepository: CameraRepository
    private lateinit var alarmPlayer: AlarmPlayer
    private lateinit var notificationHelper: NotificationHelper
    private lateinit var database: IncidentDatabase
    private lateinit var connectivityHelper: ConnectivityHelper
    private lateinit var emailSender: EmailSender
    private lateinit var prefs: PreferencesManager

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        locationRepository = LocationRepository(this)
        cameraRepository = CameraRepository(this)
        alarmPlayer = AlarmPlayer(this)
        notificationHelper = NotificationHelper(this)
        database = IncidentDatabase.getDatabase(this)
        connectivityHelper = ConnectivityHelper(this)
        emailSender = EmailSender()
        prefs = PreferencesManager(this)

        startForeground(NotificationHelper.NOTIFICATION_ID + 10, notificationHelper.getAntiTheftNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        val triggerReason = intent?.getStringExtra("trigger_reason") ?: "Unknown Trigger"
        Log.d(TAG, "Anti-theft triggered: $triggerReason")

        lifecycleScope.launch {
            executeAntiTheftProtocol(triggerReason)
        }

        return START_NOT_STICKY
    }

    private suspend fun executeAntiTheftProtocol(triggerReason: String) {
        try {
            Log.d(TAG, "Step 1: Playing alarm")
            alarmPlayer.startAlarm()

            Log.d(TAG, "Step 2: Capturing photo")
            val imageUri = cameraRepository.takePhoto(this@AntiTheftService)
            val photoPath = imageUri?.path

            Log.d(TAG, "Step 3: Getting location")
            val location = locationRepository.getCurrentLocation()

            Log.d(TAG, "Step 4: Logging incident")
            val incident = Incident(
                timestamp = System.currentTimeMillis(),
                eventType = triggerReason,
                location = location,
                imageUri = imageUri?.toString()
            )
            database.incidentDao().insert(incident)

            // Notify locally
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(3, notificationHelper.getAlertNotification("🚨 $triggerReason"))

            Log.d(TAG, "Step 5: Trying to enable WiFi")
            connectivityHelper.tryEnableWifi()

            // Wait a moment for WiFi to connect
            delay(3000)

            Log.d(TAG, "Step 6: Sending email")
            if (prefs.isEmailConfigured()) {
                val emailSent = emailSender.sendAlertEmail(
                    senderEmail = prefs.alertEmail,
                    appPassword = prefs.gmailAppPassword,
                    recipientEmail = prefs.alertEmail,
                    location = location,
                    photoPath = photoPath,
                    eventType = triggerReason
                )

                if (emailSent) {
                    Log.d(TAG, "Email sent successfully!")
                } else {
                    Log.w(TAG, "Email failed — will retry if connectivity becomes available")
                    // Retry after a delay
                    delay(10000)
                    if (connectivityHelper.isConnected()) {
                        emailSender.sendAlertEmail(
                            senderEmail = prefs.alertEmail,
                            appPassword = prefs.gmailAppPassword,
                            recipientEmail = prefs.alertEmail,
                            location = location,
                            photoPath = photoPath,
                            eventType = triggerReason
                        )
                    }
                }
            }

            // Stop alarm after 10 seconds
            delay(10000)
            alarmPlayer.stopAlarm()

        } catch (e: Exception) {
            Log.e(TAG, "Anti-theft protocol error: ${e.message}", e)
        } finally {
            // Stop the service after protocol completes
            delay(2000)
            isRunning = false
            stopSelf()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        alarmPlayer.stopAlarm()
        cameraRepository.shutdown()
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}
