package com.smartguard.app.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "incidents")
data class Incident(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long,
    val eventType: String,
    val location: String,
    val imageUri: String? = null
)
