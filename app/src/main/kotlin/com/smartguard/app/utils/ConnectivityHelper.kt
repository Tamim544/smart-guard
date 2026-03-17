package com.smartguard.app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log

class ConnectivityHelper(private val context: Context) {
    companion object {
        private const val TAG = "ConnectivityHelper"
    }

    fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    @Suppress("DEPRECATION")
    fun tryEnableWifi(): Boolean {
        return try {
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (!wifiManager.isWifiEnabled) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    // Pre-Android 10: can directly enable WiFi
                    wifiManager.isWifiEnabled = true
                    Log.d(TAG, "WiFi enabled directly")
                    true
                } else {
                    // Android 10+: cannot programmatically enable WiFi
                    // The system will handle connectivity if available
                    Log.d(TAG, "Android 10+: Cannot enable WiFi directly, relying on existing connectivity")
                    false
                }
            } else {
                Log.d(TAG, "WiFi already enabled")
                true
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to enable WiFi: ${e.message}")
            false
        }
    }
}
