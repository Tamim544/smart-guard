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
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val gyroscope: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val proximity: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

    private val _motionDetected = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val motionDetected = _motionDetected.asSharedFlow()

    private var sensitivity: Float = 15f // Default

    fun setSensitivity(value: Float) {
        sensitivity = value
    }

    fun startListening() {
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscope?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        proximity?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopListening() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val acceleration = sqrt(x * x + y * y + z * z)
                if (acceleration > sensitivity) {
                    _motionDetected.tryEmit("Sudden Movement Detected")
                }
            }
            Sensor.TYPE_GYROSCOPE -> {
                val rotationX = event.values[0]
                val rotationY = event.values[1]
                val rotationZ = event.values[2]
                if (sqrt(rotationX * rotationX + rotationY * rotationY + rotationZ * rotationZ) > 2.0) {
                    _motionDetected.tryEmit("Orientation Change Detected")
                }
            }
            Sensor.TYPE_PROXIMITY -> {
                val distance = event.values[0]
                if (distance < (proximity?.maximumRange ?: 5f)) {
                    _motionDetected.tryEmit("Proximity Change Detected")
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not needed for this implementation
    }
}
