package com.example.surfaceviewvideoplayer.data.repository;

/**
 * Репозиторий - паттерн для управления данными из разных источников
 * @param context Контекст приложения для доступа к assets и БД
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nJ\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/example/surfaceviewvideoplayer/data/repository/VideoRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "database", "Lcom/example/surfaceviewvideoplayer/data/database/AppDatabase;", "reportDao", "Lcom/example/surfaceviewvideoplayer/data/database/ReportDao;", "getAllReports", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/surfaceviewvideoplayer/data/database/ReportEntity;", "loadVideoList", "Lcom/example/surfaceviewvideoplayer/data/model/VideoItem;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveReport", "", "video", "(Lcom/example/surfaceviewvideoplayer/data/model/VideoItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class VideoRepository {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.surfaceviewvideoplayer.data.database.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.surfaceviewvideoplayer.data.database.ReportDao reportDao = null;
    
    public VideoRepository(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    /**
     * Загрузка списка видео из JSON файла в assets
     * @return Список объектов VideoItem
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object loadVideoList(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.surfaceviewvideoplayer.data.model.VideoItem>> $completion) {
        return null;
    }
    
    /**
     * Сохранение отчета о воспроизведении видео
     * @param video Видео элемент
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object saveReport(@org.jetbrains.annotations.NotNull
    com.example.surfaceviewvideoplayer.data.model.VideoItem video, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Получение всех отчетов
     * @return Flow со всеми отчетами
     */
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.surfaceviewvideoplayer.data.database.ReportEntity>> getAllReports() {
        return null;
    }
}