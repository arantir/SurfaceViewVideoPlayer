package com.example.surfaceviewvideoplayer.data.repository

import android.content.Context
import com.example.surfaceviewvideoplayer.data.database.AppDatabase
import com.example.surfaceviewvideoplayer.data.database.ReportEntity
import com.example.surfaceviewvideoplayer.data.model.VideoItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow


/**
 * Репозиторий - паттерн для управления данными из разных источников
 * @param context Контекст приложения для доступа к assets и БД
 */
class VideoRepository(private val context: Context) {
    
    // Экземпляр базы данных
    private val database = AppDatabase.getInstance(context)
    
    // DAO для работы с отчетами
    private val reportDao = database.reportDao()
    
    /**
     * Загрузка списка видео из JSON файла в assets
     * @return Список объектов VideoItem
     */
    suspend fun loadVideoList(): List<VideoItem> {
        return try {
            // Чтение JSON файла из assets
            val jsonString = context.assets.open("medialist.json")
                .bufferedReader().use { it.readText() }
            
            // Парсинг JSON в список с помощью GSON
            val type = object : TypeToken<List<VideoItem>>() {}.type
            Gson().fromJson(jsonString, type)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    
    /**
     * Сохранение отчета о воспроизведении видео
     * @param video Видео элемент
     */
    suspend fun saveReport(video: VideoItem) {
        val report = ReportEntity(
            videoId = video.videoId,
            videoName = video.videoIdentifier,
            startTime = System.currentTimeMillis() // Текущее время запуска
        )
        reportDao.insertReport(report)
    }
    
    /**
     * Получение всех отчетов
     * @return Flow со всеми отчетами
     */
    fun getAllReports(): Flow<List<ReportEntity>> {
        return reportDao.getAllReports()
    }
}