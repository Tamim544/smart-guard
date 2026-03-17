package com.smartguard.app.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    companion object {
        private const val PREFS_NAME = "smartguard_prefs"
        private const val KEY_EMAIL = "alert_email"
        private const val KEY_APP_PASSWORD = "gmail_app_password"
        private const val KEY_FAILED_ATTEMPTS_THRESHOLD = "failed_attempts_threshold"
        private const val KEY_ANTI_THEFT_ENABLED = "anti_theft_enabled"
        private const val KEY_MOTION_PROTECTION_ENABLED = "motion_protection_enabled"
        private const val KEY_SHUTDOWN_PROTECTION_ENABLED = "shutdown_protection_enabled"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var alertEmail: String
        get() = prefs.getString(KEY_EMAIL, "") ?: ""
        set(value) = prefs.edit().putString(KEY_EMAIL, value).apply()

    var gmailAppPassword: String
        get() = prefs.getString(KEY_APP_PASSWORD, "") ?: ""
        set(value) = prefs.edit().putString(KEY_APP_PASSWORD, value).apply()

    var failedAttemptsThreshold: Int
        get() = prefs.getInt(KEY_FAILED_ATTEMPTS_THRESHOLD, 3)
        set(value) = prefs.edit().putInt(KEY_FAILED_ATTEMPTS_THRESHOLD, value).apply()

    var antiTheftEnabled: Boolean
        get() = prefs.getBoolean(KEY_ANTI_THEFT_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_ANTI_THEFT_ENABLED, value).apply()

    var motionProtectionEnabled: Boolean
        get() = prefs.getBoolean(KEY_MOTION_PROTECTION_ENABLED, false)
        set(value) = prefs.edit().putBoolean(KEY_MOTION_PROTECTION_ENABLED, value).apply()

    var shutdownProtectionEnabled: Boolean
        get() = prefs.getBoolean(KEY_SHUTDOWN_PROTECTION_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_SHUTDOWN_PROTECTION_ENABLED, value).apply()

    fun isEmailConfigured(): Boolean {
        return alertEmail.isNotBlank() && gmailAppPassword.isNotBlank()
    }
}
