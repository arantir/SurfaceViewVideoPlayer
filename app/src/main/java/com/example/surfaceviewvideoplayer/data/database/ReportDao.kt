package com.example.surfaceviewvideoplayer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) для работы с таблицей reports
 * Предоставляет методы для доступа к базе данных
 */
@Dao
interface ReportDao {
    
    /**
     * Вставка нового отчета в базу данных
     * @param report Объект отчета для вставки
     */
    @Insert
    suspend fun insertReport(report: ReportEntity)
    
    /**
     * Получение всех отчетов из базы данных
     * @return Flow списка всех отчетов (для реактивного программирования)
     */
    @Query("SELECT * FROM reports ORDER BY start_time DESC")
    fun getAllReports(): Flow<List<ReportEntity>>
    
    /**
     * Получение количества отчетов для конкретного видео
     * @param videoId ID видео
     * @return Количество запусков этого видео
     */
    @Query("SELECT COUNT(*) FROM reports WHERE video_id = :videoId")
    suspend fun getReportCountForVideo(videoId: Int): Int
}