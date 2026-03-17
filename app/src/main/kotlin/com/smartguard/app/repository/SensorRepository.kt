package com.smartguard.app.repository

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.math.sqrt

class SensorRepository(context: Context) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
    private val gyroscope: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    private val _motionDetected = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val motionDetected = _motionDetected.asSharedFlow()

    // Thresholds — tuned to avoid false positives from normal handling
    private var accelerationThreshold: Float = 25f
    private val gyroscopeThreshold: Float = 5.0f

    // Cooldown to prevent repeated alarms
    private var lastTriggerTime: Long = 0L
    private val cooldownMs: Long = 10_000L // 10 seconds between triggers

    fun setSensitivity(value: Float) {
        accelerationThreshold = value
    }

    fun startListening() {
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscope?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        // Proximity sensor removed — causes too many false positives in pockets
    }

    fun stopListening() {
        sensorManager.unregisterListener(this)
    }

    private fun isInCooldown(): Boolean {
        return System.currentTimeMillis() - lastTriggerTime < cooldownMs
    }

    private fun emitEvent(eventType: String) {
        if (!isInCooldown()) {
            lastTriggerTime = System.currentTimeMillis()
            _motionDetected.tryEmit(eventType)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        when (event.sensor.type) {
            Sensor.TYPE_LINEAR_ACCELERATION -> {
                // LINEAR_ACCELERATION already has gravity removed
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val acceleration = sqrt(x * x + y * y + z * z)
                if (acceleration > accelerationThreshold) {
                    emitEvent("Sudden Movement Detected (${acceleration.toInt()} m/s²)")
                }
            }
            Sensor.TYPE_GYROSCOPE -> {
                val rotationX = event.values[0]
                val rotationY = event.values[1]
                val rotationZ = event.values[2]
                val rotationMagnitude = sqrt(rotationX * rotationX + rotationY * rotationY + rotationZ * rotationZ)
                if (rotationMagnitude > gyroscopeThreshold) {
                    emitEvent("Rapid Rotation Detected")
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not needed for this implementation
    }
}
