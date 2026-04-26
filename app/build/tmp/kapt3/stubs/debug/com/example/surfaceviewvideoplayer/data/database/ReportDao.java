package com.example.surfaceviewvideoplayer.data.database;

/**
 * Data Access Object (DAO) для работы с таблицей reports
 * Предоставляет методы для доступа к базе данных
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/example/surfaceviewvideoplayer/data/database/ReportDao;", "", "getAllReports", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/surfaceviewvideoplayer/data/database/ReportEntity;", "getReportCountForVideo", "", "videoId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertReport", "", "report", "(Lcom/example/surfaceviewvideoplayer/data/database/ReportEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface ReportDao {
    
    /**
     * Вставка нового отчета в базу данных
     * @param report Объект отчета для вставки
     */
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertReport(@org.jetbrains.annotations.NotNull
    com.example.surfaceviewvideoplayer.data.database.ReportEntity report, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Получение всех отчетов из базы данных
     * @return Flow списка всех отчетов (для реактивного программирования)
     */
    @androidx.room.Query(value = "SELECT * FROM reports ORDER BY start_time DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.surfaceviewvideoplayer.data.database.ReportEntity>> getAllReports();
    
    /**
     * Получение количества отчетов для конкретного видео
     * @param videoId ID видео
     * @return Количество запусков этого видео
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM reports WHERE video_id = :videoId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getReportCountForVideo(int videoId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}