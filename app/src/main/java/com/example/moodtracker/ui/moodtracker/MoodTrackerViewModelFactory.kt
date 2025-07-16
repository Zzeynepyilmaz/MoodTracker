package com.example.moodtracker.ui.moodtracker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moodtracker.repository.MoodRepository
import com.example.moodtracker.data.local.dao.MoodDao

class MoodTrackerViewModelFactory(
    private val moodDao: MoodDao
) : ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoodTrackerViewModel(MoodRepository(moodDao)) as T
    }
}
