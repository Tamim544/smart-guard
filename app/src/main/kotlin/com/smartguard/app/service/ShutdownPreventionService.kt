package com.smartguard.app.service

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.view.View
import androidx.lifecycle.LifecycleService
import com.smartguard.app.utils.NotificationHelper
import com.smartguard.app.utils.PreferencesManager

class ShutdownPreventionService : LifecycleService() {
    companion object {
        private const val TAG = "ShutdownPrevention"
        var isRunning = false
    }

    private var shutdownReceiver: BroadcastReceiver? = null
    private lateinit var notificationHelper: NotificationHelper

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        notificationHelper = NotificationHelper(this)

        startForeground(
            NotificationHelper.NOTIFICATION_ID + 20,
            notificationHelper.getShutdownProtectionNotification()
        )

        registerShutdownReceiver()
        Log.d(TAG, "Shutdown prevention service started")
    }

    private fun registerShutdownReceiver() {
        shutdownReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    Intent.ACTION_SHUTDOWN -> {
                        Log.d(TAG, "Shutdown detected! Triggering emergency protocol")
                        // When shutdown is detected, immediately trigger anti-theft
                        val prefs = PreferencesManager(this@ShutdownPreventionService)
                        if (prefs.isEmailConfigured()) {
                            val serviceIntent = Intent(
                                this@ShutdownPreventionService,
                                AntiTheftService::class.java
                            ).apply {
                                putExtra("trigger_reason", "Emergency: Shutdown Attempted")
                            }
                            try {
                                startForegroundService(serviceIntent)
                            } catch (e: Exception) {
                                Log.e(TAG, "Failed to start emergency service: ${e.message}")
                            }
                        }
                    }
                    Intent.ACTION_SCREEN_OFF -> {
                        Log.d(TAG, "Screen turned off — monitoring")
                    }
                }
            }
        }

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SHUTDOWN)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        registerReceiver(shutdownReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        shutdownReceiver?.let {
            try {
                unregisterReceiver(it)
            } catch (e: Exception) {
                Log.e(TAG, "Error unregistering receiver: ${e.message}")
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}
