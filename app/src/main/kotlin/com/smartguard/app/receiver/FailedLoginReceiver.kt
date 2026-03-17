package com.smartguard.app.receiver

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.smartguard.app.service.AntiTheftService
import com.smartguard.app.utils.PreferencesManager

class FailedLoginReceiver : DeviceAdminReceiver() {
    companion object {
        private const val TAG = "FailedLoginReceiver"
    }

    override fun onPasswordFailed(context: Context, intent: Intent) {
        super.onPasswordFailed(context, intent)

        val prefs = PreferencesManager(context)
        val manager = getManager(context)
        val componentName = getWho(context)

        val failedAttempts = manager.currentFailedPasswordAttempts
        val threshold = prefs.failedAttemptsThreshold

        Log.d(TAG, "Password failed. Attempt #$failedAttempts / threshold: $threshold")

        if (failedAttempts >= threshold && prefs.antiTheftEnabled && prefs.isEmailConfigured()) {
            Log.d(TAG, "Threshold reached! Triggering anti-theft protocol.")
            // Start the anti-theft service
            val serviceIntent = Intent(context, AntiTheftService::class.java).apply {
                putExtra("trigger_reason", "Wrong Password ($failedAttempts attempts)")
            }
            try {
                context.startForegroundService(serviceIntent)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to start AntiTheftService: ${e.message}")
            }
        }
    }

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Log.d(TAG, "Device Admin enabled")
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        Log.d(TAG, "Device Admin disabled")
    }
}
