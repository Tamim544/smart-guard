package com.smartguard.app.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.smartguard.app.databinding.ActivityDashboardBinding
import com.smartguard.app.viewmodel.SecurityViewModel

class SecurityDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewModel: SecurityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SecurityViewModel::class.java]

        setupUI()
    }

    private fun setupUI() {
        viewModel.isServiceRunning.observe(this) { isRunning ->
            if (!isRunning) {
                finish() // Go back if service stopped
            }
        }

        binding.btnDeactivate.setOnClickListener {
            viewModel.toggleSecurity(this)
        }
    }
}
