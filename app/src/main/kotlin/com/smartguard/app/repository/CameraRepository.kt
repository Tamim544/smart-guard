package com.smartguard.app.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CameraRepository(private val context: Context) {
    private var imageCapture: ImageCapture? = null
    private val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()

    suspend fun takePhoto(lifecycleOwner: LifecycleOwner): Uri? {
        val cameraProvider = getCameraProvider() ?: return null

        imageCapture = ImageCapture.Builder().build()

        val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                imageCapture
            )
        } catch (exc: Exception) {
            Log.e("CameraRepository", "Use case binding failed", exc)
            return null
        }

        return suspendCoroutine { continuation ->
            val photoFile = File(
                context.filesDir,
                SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US)
                    .format(System.currentTimeMillis()) + ".jpg"
            )

            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            imageCapture?.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        Log.e("CameraRepository", "Photo capture failed: ${exc.message}", exc)
                        continuation.resume(null)
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = Uri.fromFile(photoFile)
                        Log.d("CameraRepository", "Photo capture succeeded: $savedUri")
                        continuation.resume(savedUri)
                    }
                }
            )
        }
    }

    private suspend fun getCameraProvider(): ProcessCameraProvider? = suspendCoroutine { continuation ->
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            try {
                continuation.resume(cameraProviderFuture.get())
            } catch (e: Exception) {
                continuation.resume(null)
            }
        }, ContextCompat.getMainExecutor(context))
    }

    fun shutdown() {
        cameraExecutor.shutdown()
    }
}
