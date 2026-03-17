package com.smartguard.app.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.smartguard.app.R
import com.smartguard.app.activities.MainActivity

class NotificationHelper(private val context: Context) {
    companion object {
        const val CHANNEL_ID = "smartguard_channel"
        const val ALERT_CHANNEL_ID = "smartguard_alert_channel"
        const val NOTIFICATION_ID = 1
    }

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Normal channel
            val channel = NotificationChannel(
                CHANNEL_ID,
                "SmartGuard Security",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Monitoring phone movement"
            }
            notificationManager.createNotificationChannel(channel)

            // High-priority alert channel
            val alertChannel = NotificationChannel(
                ALERT_CHANNEL_ID,
                "SmartGuard Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Security alerts and anti-theft notifications"
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(alertChannel)
        }
    }

    private fun getMainPendingIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun getForegroundNotification(): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("SmartGuard Active")
            .setContentText("Monitoring for unauthorized movement...")
            .setSmallIcon(android.R.drawable.ic_lock_lock)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(getMainPendingIntent())
            .setOngoing(true)
            .build()
    }

    fun getAntiTheftNotification(): Notification {
        return NotificationCompat.Builder(context, ALERT_CHANNEL_ID)
            .setContentTitle("🚨 Anti-Theft Active")
            .setContentText("Capturing evidence and sending alerts...")
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getMainPendingIntent())
            .setOngoing(true)
            .build()
    }

    fun getShutdownProtectionNotification(): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("🔒 Shutdown Protection")
            .setContentText("Device is protected from unauthorized shutdown")
            .setSmallIcon(android.R.drawable.ic_lock_lock)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(getMainPendingIntent())
            .setOngoing(true)
            .build()
    }

    fun getAlertNotification(message: String): Notification {
        return NotificationCompat.Builder(context, ALERT_CHANNEL_ID)
            .setContentTitle("🚨 Security Alert!")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
    }
}
