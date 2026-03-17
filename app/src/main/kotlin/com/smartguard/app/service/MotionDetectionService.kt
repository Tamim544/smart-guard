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
import com.smartguard.app.repository.SensorRepository
import com.smartguard.app.utils.AlarmPlayer
import com.smartguard.app.utils.NotificationHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MotionDetectionService : LifecycleService() {
    companion object {
        var isRunning = false
    }

    private lateinit var sensorRepository: SensorRepository
    private lateinit var locationRepository: LocationRepository
    private lateinit var cameraRepository: CameraRepository
    private lateinit var alarmPlayer: AlarmPlayer
    private lateinit var notificationHelper: NotificationHelper
    private lateinit var database: IncidentDatabase

    private var isAlertActive = false

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        sensorRepository = SensorRepository(this)
        locationRepository = LocationRepository(this)
        cameraRepository = CameraRepository(this)
        alarmPlayer = AlarmPlayer(this)
        notificationHelper = NotificationHelper(this)
        database = IncidentDatabase.getDatabase(this)

        startForeground(NotificationHelper.NOTIFICATION_ID, notificationHelper.getForegroundNotification())

        observeSensors()
    }

    private fun observeSensors() {
        lifecycleScope.launch {
            sensorRepository.motionDetected.collectLatest { eventType ->
                if (!isAlertActive) {
                    triggerSecurityProtocol(eventType)
                }
            }
        }
        sensorRepository.startListening()
    }

    private fun triggerSecurityProtocol(eventType: String) {
        isAlertActive = true
        Log.d("MotionService", "Alert Triggered: $eventType")

        lifecycleScope.launch {
            // 1. Play Alarm
            alarmPlayer.startAlarm()

            // 2. Capture Location
            val location = locationRepository.getCurrentLocation()

            // 3. Capture Photo
            val imageUri = cameraRepository.takePhoto(this@MotionDetectionService)

            // 4. Log Incident to Database
            val incident = Incident(
                timestamp = System.currentTimeMillis(),
                eventType = eventType,
                location = location,
                imageUri = imageUri?.toString()
            )
            database.incidentDao().insert(incident)

            // 5. Notify user of alert
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(2, notificationHelper.getAlertNotification(eventType))

            // Keep alert active for some time or until user stops
            delay(5000)
            isAlertActive = false
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        sensorRepository.stopListening()
        alarmPlayer.stopAlarm()
        cameraRepository.shutdown()
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}
