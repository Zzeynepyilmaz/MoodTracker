package com.example.moodtracker.ui.moodtracker

import com.example.moodtracker.domain.model.MoodEntry
import com.example.moodtracker.domain.model.MoodType

data class MoodTrackerState(
    val selectedMood: MoodType? = null,
    val note: String = "",
    val isLoading: Boolean = false,
    val moodHistory: List<MoodEntry> = emptyList(),
    val hasTodayEntry: Boolean = false
)
