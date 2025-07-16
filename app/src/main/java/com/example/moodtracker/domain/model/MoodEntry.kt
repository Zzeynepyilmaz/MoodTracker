package com.example.moodtracker.domain.model

import java.time.LocalDate

data class MoodEntry(
    val date: LocalDate,
    val mood: MoodType,
    val note: String
)

