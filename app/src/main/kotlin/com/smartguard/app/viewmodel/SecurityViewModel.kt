package com.smartguard.app.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import android.app.ActivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.smartguard.app.database.Incident
import com.smartguard.app.database.IncidentDatabase
import com.smartguard.app.service.MotionDetectionService

class SecurityViewModel(application: Application) : AndroidViewModel(application) {
    private val database = IncidentDatabase.getDatabase(application)
    private val incidentDao = database.incidentDao()

    val incidents: LiveData<List<Incident>> = incidentDao.getAllIncidents().asLiveData()

    private val _isServiceRunning = MutableLiveData<Boolean>(MotionDetectionService.isRunning)
    val isServiceRunning: LiveData<Boolean> = _isServiceRunning

    fun toggleSecurity(context: Context) {
        val intent = Intent(context, MotionDetectionService::class.java)
        if (_isServiceRunning.value == true) {
            context.stopService(intent)
            _isServiceRunning.value = false
        } else {
            ContextCompat.startForegroundService(context, intent)
            _isServiceRunning.value = true
        }
    }

    fun setServiceStatus(isRunning: Boolean) {
        _isServiceRunning.value = isRunning
    }
}
