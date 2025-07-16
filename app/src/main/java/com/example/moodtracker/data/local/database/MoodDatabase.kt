package com.example.moodtracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moodtracker.data.local.dao.MoodDao
import com.example.moodtracker.data.local.entity.MoodEntity
import com.example.moodtracker.util.Converters

@Database(entities = [MoodEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MoodDatabase : RoomDatabase() {
    abstract fun moodDao(): MoodDao
}