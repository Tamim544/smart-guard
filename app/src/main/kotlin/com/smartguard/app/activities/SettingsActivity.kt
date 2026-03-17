package com.smartguard.app.activities

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.smartguard.app.databinding.ActivitySettingsBinding
import com.smartguard.app.receiver.FailedLoginReceiver
import com.smartguard.app.utils.ConnectivityHelper
import com.smartguard.app.utils.EmailSender
import com.smartguard.app.utils.PreferencesManager
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var prefs: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = PreferencesManager(this)

        loadSettings()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        updateSetupStatus()
    }

    private fun loadSettings() {
        binding.etEmail.setText(prefs.alertEmail)
        binding.etAppPassword.setText(prefs.gmailAppPassword)
        binding.etThreshold.setText(prefs.failedAttemptsThreshold.toString())
        binding.switchAntiTheft.isChecked = prefs.antiTheftEnabled
        binding.switchShutdown.isChecked = prefs.shutdownProtectionEnabled
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            saveSettings()
        }

        binding.btnTestEmail.setOnClickListener {
            sendTestEmail()
        }

        binding.btnHowToGetPassword.setOnClickListener {
            showAppPasswordGuide()
        }
    }

    private fun saveSettings() {
        val email = binding.etEmail.text.toString().trim()
        val appPassword = binding.etAppPassword.text.toString().trim()
        val threshold = binding.etThreshold.text.toString().toIntOrNull() ?: 3

        if (email.isBlank()) {
            binding.etEmail.error = "Email is required"
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Invalid email address"
            return
        }

        if (appPassword.isBlank()) {
            binding.etAppPassword.error = "App password is required"
            return
        }

        if (threshold < 1 || threshold > 10) {
            binding.etThreshold.error = "Must be between 1 and 10"
            return
        }

        prefs.alertEmail = email
        prefs.gmailAppPassword = appPassword
        prefs.failedAttemptsThreshold = threshold
        prefs.antiTheftEnabled = binding.switchAntiTheft.isChecked
        prefs.shutdownProtectionEnabled = binding.switchShutdown.isChecked

        Toast.makeText(this, "✅ Settings saved!", Toast.LENGTH_SHORT).show()
        updateSetupStatus()
    }

    private fun sendTestEmail() {
        val email = binding.etEmail.text.toString().trim()
        val appPassword = binding.etAppPassword.text.toString().trim()

        if (email.isBlank() || appPassword.isBlank()) {
            Toast.makeText(this, "Please save settings first", Toast.LENGTH_SHORT).show()
            return
        }

        val connectivity = ConnectivityHelper(this)
        if (!connectivity.isConnected()) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "📤 Sending test email...", Toast.LENGTH_SHORT).show()

        lifecycleScope.launch {
            val sender = EmailSender()
            val success = sender.sendAlertEmail(
                senderEmail = email,
                appPassword = appPassword,
                recipientEmail = email,
                location = "Test Location (0.0, 0.0)",
                photoPath = null,
                eventType = "Test Alert — SmartGuard is working!"
            )

            if (success) {
                Toast.makeText(this@SettingsActivity, "✅ Test email sent! Check your inbox.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@SettingsActivity, "❌ Email failed. Check your App Password.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showAppPasswordGuide() {
        AlertDialog.Builder(this)
            .setTitle("How to Get a Gmail App Password")
            .setMessage("""
                Follow these steps:
                
                1. Go to your Google Account settings
                2. Navigate to Security → 2-Step Verification
                3. Enable 2-Step Verification if not already enabled
                4. Go back to Security → App passwords
                5. Select "Mail" and your device
                6. Click "Generate"
                7. Copy the 16-character password
                8. Paste it in the App Password field above
                
                Note: You must use a Gmail account with 2-Step Verification enabled.
            """.trimIndent())
            .setPositiveButton("Open Google Settings") { _, _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://myaccount.google.com/apppasswords"))
                startActivity(intent)
            }
            .setNegativeButton("Close", null)
            .show()
    }

    private fun updateSetupStatus() {
        val statusLines = mutableListOf<String>()

        // Check email
        if (prefs.isEmailConfigured()) {
            statusLines.add("✅ Email configured: ${prefs.alertEmail}")
        } else {
            statusLines.add("❌ Email not configured")
        }

        // Check Device Admin
        val dpm = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val adminComponent = ComponentName(this, FailedLoginReceiver::class.java)
        if (dpm.isAdminActive(adminComponent)) {
            statusLines.add("✅ Device Admin enabled")
        } else {
            statusLines.add("❌ Device Admin not enabled (set up from main screen)")
        }

        // Features
        statusLines.add(if (prefs.antiTheftEnabled) "✅ Wrong password detection: ON" else "⚪ Wrong password detection: OFF")
        statusLines.add(if (prefs.shutdownProtectionEnabled) "✅ Shutdown prevention: ON" else "⚪ Shutdown prevention: OFF")

        binding.tvSetupStatus.text = statusLines.joinToString("\n")
    }
}
