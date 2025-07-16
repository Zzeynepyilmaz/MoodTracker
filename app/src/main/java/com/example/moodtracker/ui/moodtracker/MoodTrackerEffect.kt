package com.example.moodtracker.ui.moodtracker

sealed class MoodTrackerEffect {
    object ShowSaveSuccessMessage : MoodTrackerEffect()
    data class ShowError(val message: String) : MoodTrackerEffect()
}