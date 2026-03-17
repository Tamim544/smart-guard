package com.smartguard.app.activities

import android.Manifest
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.smartguard.app.databinding.ActivityMainBinding
import com.smartguard.app.receiver.FailedLoginReceiver
import com.smartguard.app.service.MotionDetectionService
import com.smartguard.app.service.ShutdownPreventionService
import com.smartguard.app.utils.PreferencesManager
import com.smartguard.app.viewmodel.SecurityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SecurityViewModel
    private lateinit var prefs: PreferencesManager

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            toggleSecurity()
        } else {
            Toast.makeText(this, "Permissions required for security mode", Toast.LENGTH_LONG).show()
        }
    }

    private val enableAdminLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (isDeviceAdminActive()) {
            Toast.makeText(this, "✅ Device Admin enabled!", Toast.LENGTH_SHORT).show()
            // Start shutdown prevention if enabled
            if (prefs.shutdownProtectionEnabled) {
                startShutdownProtection()
            }
        } else {
            Toast.makeText(this, "Device Admin is required for anti-theft", Toast.LENGTH_LONG).show()
        }
        updateUI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SecurityViewModel::class.java]
        prefs = PreferencesManager(this)

        setupUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.setServiceStatus(MotionDetectionService.isRunning)
        updateUI()
    }

    private fun setupUI() {
        viewModel.isServiceRunning.observe(this) { isRunning ->
            binding.btnActivate.text = if (isRunning) "DEACTIVATE PROTECTION" else "ACTIVATE PROTECTION"
            binding.tvStatus.text = "Status: ${if (isRunning) "ACTIVE ✅" else "OFF"}"
        }

        binding.btnActivate.setOnClickListener {
            checkPermissionsAndToggle()
        }

        binding.btnEnableAdmin.setOnClickListener {
            requestDeviceAdmin()
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.btnViewHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }

    private fun updateUI() {
        val adminActive = isDeviceAdminActive()
        val emailConfigured = prefs.isEmailConfigured()

        // Show/hide admin button based on status
        binding.btnEnableAdmin.visibility = if (adminActive) View.GONE else View.VISIBLE

        // Show setup info
        val infoLines = mutableListOf<String>()
        if (adminActive) {
            infoLines.add("🔐 Device Admin: Active")
        } else {
            infoLines.add("⚠️ Device Admin: Not enabled")
        }
        if (emailConfigured) {
            infoLines.add("📧 Email alerts: Configured")
        } else {
            infoLines.add("⚠️ Email alerts: Not configured (go to Settings)")
        }
        if (ShutdownPreventionService.isRunning) {
            infoLines.add("🔒 Shutdown protection: Active")
        }

        binding.tvSetupInfo.text = infoLines.joinToString("\n")
    }

    private fun isDeviceAdminActive(): Boolean {
        val dpm = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val adminComponent = ComponentName(this, FailedLoginReceiver::class.java)
        return dpm.isAdminActive(adminComponent)
    }

    private fun requestDeviceAdmin() {
        val adminComponent = ComponentName(this, FailedLoginReceiver::class.java)
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
            putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent)
            putExtra(
                DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "SmartGuard needs Device Admin access to detect wrong password attempts and protect your device from theft."
            )
        }
        enableAdminLauncher.launch(intent)
    }

    private fun startShutdownProtection() {
        if (!ShutdownPreventionService.isRunning) {
            val intent = Intent(this, ShutdownPreventionService::class.java)
            ContextCompat.startForegroundService(this, intent)
        }
    }

    private fun checkPermissionsAndToggle() {
        val permissions = mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        val allGranted = permissions.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }

        if (allGranted) {
            toggleSecurity()
        } else {
            requestPermissionLauncher.launch(permissions.toTypedArray())
        }
    }

    private fun toggleSecurity() {
        val wasRunning = viewModel.isServiceRunning.value ?: false
        viewModel.toggleSecurity(this)
        if (!wasRunning) {
            startActivity(Intent(this, SecurityDashboardActivity::class.java))

            // Also start shutdown protection if enabled and admin is active
            if (prefs.shutdownProtectionEnabled && isDeviceAdminActive()) {
                startShutdownProtection()
            }
        }
    }
}
