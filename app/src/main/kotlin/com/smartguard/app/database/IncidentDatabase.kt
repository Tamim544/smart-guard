package com.smartguard.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Incident::class], version = 1, exportSchema = false)
abstract class IncidentDatabase : RoomDatabase() {
    abstract fun incidentDao(): IncidentDao

    companion object {
        @Volatile
        private var INSTANCE: IncidentDatabase? = null

        fun getDatabase(context: Context): IncidentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IncidentDatabase::class.java,
                    "incident_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
