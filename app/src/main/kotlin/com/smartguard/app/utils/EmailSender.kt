package com.smartguard.app.utils

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.Multipart
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class EmailSender {
    companion object {
        private const val TAG = "EmailSender"
        private const val SMTP_HOST = "smtp.gmail.com"
        private const val SMTP_PORT = "587"
    }

    suspend fun sendAlertEmail(
        senderEmail: String,
        appPassword: String,
        recipientEmail: String,
        location: String,
        photoPath: String?,
        eventType: String
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val props = Properties().apply {
                put("mail.smtp.host", SMTP_HOST)
                put("mail.smtp.port", SMTP_PORT)
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
                put("mail.smtp.ssl.trust", SMTP_HOST)
                put("mail.smtp.connectiontimeout", "10000")
                put("mail.smtp.timeout", "10000")
            }

            val session = Session.getInstance(props, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(senderEmail, appPassword)
                }
            })

            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(senderEmail))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail))
                subject = "🚨 SmartGuard Security Alert — $eventType"
            }

            val multipart: Multipart = MimeMultipart()

            // Text body
            val textPart = MimeBodyPart().apply {
                setText(buildEmailBody(eventType, location), "utf-8", "html")
            }
            multipart.addBodyPart(textPart)

            // Attach photo if available
            if (photoPath != null) {
                val photoFile = File(photoPath)
                if (photoFile.exists()) {
                    val attachmentPart = MimeBodyPart().apply {
                        attachFile(photoFile)
                        fileName = "intruder_photo.jpg"
                    }
                    multipart.addBodyPart(attachmentPart)
                }
            }

            message.setContent(multipart)

            Transport.send(message)
            Log.d(TAG, "Alert email sent successfully to $recipientEmail")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Failed to send email: ${e.message}", e)
            false
        }
    }

    private fun buildEmailBody(eventType: String, location: String): String {
        return """
            <html>
            <body style="font-family: Arial, sans-serif; padding: 20px;">
                <h2 style="color: #D32F2F;">🚨 SmartGuard Security Alert</h2>
                <hr>
                <p><strong>Event:</strong> $eventType</p>
                <p><strong>Location:</strong> <a href="https://www.google.com/maps?q=$location">$location</a></p>
                <p><strong>Time:</strong> ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date())}</p>
                <hr>
                <p style="color: #666;">If a photo was captured, it is attached to this email.</p>
                <p style="color: #999; font-size: 12px;">Sent by SmartGuard Anti-Theft System</p>
            </body>
            </html>
        """.trimIndent()
    }
}
