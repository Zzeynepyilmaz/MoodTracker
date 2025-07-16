package com.example.moodtracker.ui.moodtracker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moodtracker.domain.model.MoodEntry
import com.example.moodtracker.domain.model.MoodType
import java.time.LocalDate
import androidx.compose.runtime.getValue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodCalendarMainScreen(viewModel: MoodTrackerViewModel) {
    val state by viewModel.state.collectAsState()
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    val selectedMoodEntry = state.moodHistory.firstOrNull { it.date == selectedDate }
    val isToday = selectedDate == LocalDate.now()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // TAKVİM
        CalendarGrid(
            moodEntries = state.moodHistory,
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // GÜN BİLGİSİ
        Text("📅 Seçilen Gün: $selectedDate", style = MaterialTheme.typography.titleMedium)

        if (selectedMoodEntry != null) {
            Text("Mood: ${selectedMoodEntry.mood.name}")
            Text("Not: ${selectedMoodEntry.note}")
        } else if (isToday) {
            // Eğer bugünkü günse ve kayıt yoksa → mood gir
            MoodInputSection(viewModel)
        } else {
            Text("Bu güne ait mood kaydı yok.")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarGrid(
    moodEntries: List<MoodEntry>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val today = LocalDate.now()
    val currentMonth = today.withDayOfMonth(1)
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfWeek = currentMonth.dayOfWeek.value % 7
    val totalCells = daysInMonth + firstDayOfWeek
    val rows = (totalCells / 7f).let { if (it % 1 == 0f) it.toInt() else it.toInt() + 1 }

    Column {
        for (row in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (col in 0..6) {
                    val dayNumber = row * 7 + col - firstDayOfWeek + 1
                    if (dayNumber in 1..daysInMonth) {
                        val date = currentMonth.withDayOfMonth(dayNumber)
                        val mood = moodEntries.firstOrNull { it.date == date }?.mood
                        val isSelected = date == selectedDate
                        DayBox(date, mood, isSelected = isSelected) { onDateSelected(date) }
                    } else {
                        Spacer(modifier = Modifier.size(40.dp))
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayBox(
    date: LocalDate,
    mood: MoodType?,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val moodColor = when (mood) {
        MoodType.HAPPY -> Color.Yellow
        MoodType.SAD -> Color.Blue
        MoodType.ANGRY -> Color.Red
        MoodType.TIRED -> Color.Gray
        MoodType.CALM -> Color.Green
        MoodType.STRESSED -> Color(0xFFFFA726)
        else -> Color.LightGray
    }

    val borderModifier = if (isSelected) Modifier.background(Color.DarkGray) else Modifier

    Box(
        modifier = Modifier
            .size(40.dp)
            .padding(2.dp)
            .then(borderModifier)
            .clip(CircleShape)
            .background(moodColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(text = date.dayOfMonth.toString(), style = MaterialTheme.typography.bodySmall)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodInputSection(viewModel: MoodTrackerViewModel) {
    val state by viewModel.state.collectAsState()

    Text("Bugünkü Mood'unuzu Girin", style = MaterialTheme.typography.titleMedium)
    Spacer(modifier = Modifier.height(8.dp))

    MoodType.values().forEach { mood ->
        MoodChip(
            text = mood.name.lowercase().replaceFirstChar { it.uppercase() },
            selected = state.selectedMood == mood,
            onClick = { viewModel.onIntent(MoodTrackerIntent.SelectMood(mood)) }
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = state.note,
        onValueChange = { viewModel.onIntent(MoodTrackerIntent.EnterNote(it)) },
        label = { Text("Not") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = { viewModel.onIntent(MoodTrackerIntent.SaveMood) },
        enabled = state.selectedMood != null && !state.hasTodayEntry
    ) {
        Text("Kaydet")
    }
}
@Composable
fun MoodChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (selected) MaterialTheme.colorScheme.primary else Color.LightGray
    val contentColor = if (selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = text, color = contentColor)
    }
}
