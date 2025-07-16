package com.example.moodtracker.domain.model

import java.time.LocalDate

data class MoodTrackerStateEntry(
    val date: LocalDate,
    val mood: MoodType?
)

