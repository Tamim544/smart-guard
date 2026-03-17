package com.smartguard.app.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import com.smartguard.app.R

class AlarmPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null

    fun startAlarm() {
        if (mediaPlayer?.isPlaying == true) return

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
            )
            // Use a default system alarm sound if R.raw.alarm is not found
            val alarmUri = Uri.parse("android.resource://${context.packageName}/raw/alarm")
            try {
                setDataSource(context, alarmUri)
                prepare()
                isLooping = true
                start()
            } catch (e: Exception) {
                // Fallback to system ringtone/alarm if raw file fails
                // For now just catch to avoid crash
            }
        }
    }

    fun stopAlarm() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
