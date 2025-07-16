package com.example.moodtracker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodtracker.ui.moodtracker.MoodCalendarMainScreen
import com.example.moodtracker.ui.moodtracker.MoodTrackerViewModel
import com.example.moodtracker.ui.moodtracker.MoodTrackerViewModelFactory
import com.example.moodtracker.ui.theme.MoodTrackerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoodTrackerTheme {
                val app = applicationContext as MoodTrackerApp
                val viewModel: MoodTrackerViewModel = viewModel(
                    factory = MoodTrackerViewModelFactory(app.database.moodDao())
                )

                MoodCalendarMainScreen(viewModel)
            }
        }
    }
}

