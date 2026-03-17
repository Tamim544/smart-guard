package com.smartguard.app.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.smartguard.app.databinding.ActivityMainBinding
import com.smartguard.app.viewmodel.SecurityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SecurityViewModel

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            toggleSecurity()
        } else {
            Toast.makeText(this, "Permissions required for security mode", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SecurityViewModel::class.java]

        setupUI()
    }

    private fun setupUI() {
        viewModel.isServiceRunning.observe(this) { isRunning ->
            binding.btnActivate.text = if (isRunning) "DEACTIVATE PROTECTION" else "ACTIVATE PROTECTION"
            binding.tvStatus.text = "Status: ${if (isRunning) "ACTIVE" else "OFF"}"
        }

        binding.btnActivate.setOnClickListener {
            checkPermissionsAndToggle()
        }

        binding.btnViewHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            // Android 14+ needs more specific foreground service permissions
            // These are already in manifest, but good to check if needed.
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
        }
    }
}
