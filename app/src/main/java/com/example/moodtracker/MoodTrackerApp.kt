package com.example.moodtracker

import android.app.Application
import androidx.room.Room
import com.example.moodtracker.data.local.database.MoodDatabase

class MoodTrackerApp : Application() {

    lateinit var database: MoodDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            MoodDatabase::class.java,
            "mood_db"
        ).build()
    }
}
