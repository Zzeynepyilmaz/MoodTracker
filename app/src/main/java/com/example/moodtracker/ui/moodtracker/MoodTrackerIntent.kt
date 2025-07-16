package com.example.moodtracker.ui.moodtracker

import com.example.moodtracker.domain.model.MoodType

sealed class MoodTrackerIntent {
    data class SelectMood(val mood: MoodType) : MoodTrackerIntent()
    data class EnterNote(val note: String) : MoodTrackerIntent()
    object SaveMood : MoodTrackerIntent()
    object LoadMoodEntries : MoodTrackerIntent()
}