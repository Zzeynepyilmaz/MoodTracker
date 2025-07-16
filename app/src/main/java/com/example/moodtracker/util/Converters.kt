package com.example.moodtracker.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.example.moodtracker.domain.model.MoodType
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun fromDate(date: LocalDate): String = date.toString()

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toDate(dateString: String): LocalDate = LocalDate.parse(dateString)

    @TypeConverter
    fun fromMoodType(mood: MoodType): String = mood.name

    @TypeConverter
    fun toMoodType(name: String): MoodType = MoodType.valueOf(name)
}