package com.example.moodtracker.ui.moodtracker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtracker.data.local.entity.MoodEntity
import com.example.moodtracker.domain.model.MoodType
import com.example.moodtracker.repository.MoodRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MoodTrackerViewModel(
    private val repository: MoodRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MoodTrackerState())
    val state: StateFlow<MoodTrackerState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MoodTrackerEffect>()
    val effect: SharedFlow<MoodTrackerEffect> = _effect.asSharedFlow()

    init {
        onIntent(MoodTrackerIntent.LoadMoodEntries)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onIntent(intent: MoodTrackerIntent) {
        when (intent) {
            is MoodTrackerIntent.SelectMood -> {
                _state.update { it.copy(selectedMood = intent.mood) }
            }
            is MoodTrackerIntent.EnterNote -> {
                _state.update { it.copy(note = intent.note) }
            }
            is MoodTrackerIntent.SaveMood -> {
                saveMood()
            }
            is MoodTrackerIntent.LoadMoodEntries -> {
                loadMoodEntries()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveMood() {
        viewModelScope.launch {
            val mood = _state.value.selectedMood ?: return@launch
            val entry = MoodEntity(
                date = LocalDate.now(),
                mood = mood,
                note = _state.value.note
            )
            repository.insertMood(entry)
            _effect.emit(MoodTrackerEffect.ShowSaveSuccessMessage)
            _state.update { it.copy(note = "", selectedMood = null) }
        }
    }

    private fun loadMoodEntries() {
        viewModelScope.launch {
            repository.getAllMoods()
                .collect { moodList ->
                    val today = LocalDate.now()
                    val hasToday = moodList.any { it.date == today }

                    _state.update {
                        it.copy(
                            moodHistory = moodList.map { it.toDomain() },
                            isLoading = false,
                            hasTodayEntry = hasToday // 👈 burada güncelliyoruz
                        )
                    }
                }
        }
    }

    // MoodEntity to MoodEntry (domain model)
    private fun MoodEntity.toDomain() = com.example.moodtracker.domain.model.MoodEntry(
        date = date,
        mood = mood,
        note = note
    )
}
