package com.example.moodtracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moodtracker.domain.model.MoodType
import java.time.LocalDate

@Entity(tableName = "moods")
data class MoodEntity(
    @PrimaryKey val date: LocalDate, // aynı gün sadece 1 kayıt
    val mood: MoodType,
    val note: String
)
