package com.example.moodtracker.repository

import com.example.moodtracker.data.local.dao.MoodDao
import com.example.moodtracker.data.local.entity.MoodEntity
import kotlinx.coroutines.flow.Flow

class MoodRepository(private val dao: MoodDao) {

    suspend fun insertMood(entry: MoodEntity) = dao.insertMood(entry)

    fun getAllMoods(): Flow<List<MoodEntity>> = dao.getAllMoods()
}