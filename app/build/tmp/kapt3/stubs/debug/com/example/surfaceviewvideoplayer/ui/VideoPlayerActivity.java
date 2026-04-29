package com.example.surfaceviewvideoplayer.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fH\u0002J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\fH\u0002J\u0012\u0010\u001c\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0018H\u0014J\b\u0010 \u001a\u00020\u0018H\u0002J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\fH\u0002J(\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010\'\u001a\u00020%H\u0016J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010)\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/example/surfaceviewvideoplayer/ui/VideoPlayerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/view/SurfaceHolder$Callback;", "()V", "debugText", "Landroid/widget/TextView;", "handler", "Landroid/os/Handler;", "isTransitioning", "", "logList", "", "", "mediaPlayer", "Landroid/media/MediaPlayer;", "progressBar", "Landroid/widget/ProgressBar;", "surfaceHolder", "Landroid/view/SurfaceHolder;", "surfaceView", "Landroid/view/SurfaceView;", "viewModel", "Lcom/example/surfaceviewvideoplayer/ui/viewmodel/VideoPlayerViewModel;", "addLog", "", "msg", "isVideoValid", "videoFileName", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "releaseMediaPlayer", "startVideoPlayback", "surfaceChanged", "holder", "format", "", "width", "height", "surfaceCreated", "surfaceDestroyed", "app_debug"})
public final class VideoPlayerActivity extends androidx.appcompat.app.AppCompatActivity implements android.view.SurfaceHolder.Callback {
    
    /**
     * SurfaceView из XML
     */
    private android.view.SurfaceView surfaceView;
    
    /**
     * Индикатор загрузки
     */
    private android.widget.ProgressBar progressBar;
    
    /**
     * Поле для вывода лога
     */
    private android.widget.TextView debugText;
    
    /**
     * MediaPlayer для воспроизведения видео
     */
    @org.jetbrains.annotations.Nullable
    private android.media.MediaPlayer mediaPlayer;
    
    /**
     * Холдер поверхности
     */
    @org.jetbrains.annotations.Nullable
    private android.view.SurfaceHolder surfaceHolder;
    
    /**
     * ViewModel с логикой воспроизведения
     */
    private com.example.surfaceviewvideoplayer.ui.viewmodel.VideoPlayerViewModel viewModel;
    
    /**
     * Список сообщений лога (последние 20)
     */
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> logList = null;
    
    /**
     * Handler для задержки перехода к следующему видео
     */
    @org.jetbrains.annotations.NotNull
    private final android.os.Handler handler = null;
    
    /**
     * Флаг, идёт ли переход к следующему видео
     */
    private boolean isTransitioning = false;
    
    public VideoPlayerActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    /**
     * Добавляет сообщение в лог
     */
    private final void addLog(java.lang.String msg) {
    }
    
    /**
     * Проверяет, можно ли воспроизвести видео
     */
    private final boolean isVideoValid(java.lang.String videoFileName) {
        return false;
    }
    
    /**
     * Запускает воспроизведение видео
     */
    private final void startVideoPlayback(java.lang.String videoFileName) {
    }
    
    /**
     * Освобождает ресурсы MediaPlayer
     */
    private final void releaseMediaPlayer() {
    }
    
    @java.lang.Override
    public void surfaceCreated(@org.jetbrains.annotations.NotNull
    android.view.SurfaceHolder holder) {
    }
    
    @java.lang.Override
    public void surfaceChanged(@org.jetbrains.annotations.NotNull
    android.view.SurfaceHolder holder, int format, int width, int height) {
    }
    
    @java.lang.Override
    public void surfaceDestroyed(@org.jetbrains.annotations.NotNull
    android.view.SurfaceHolder holder) {
    }
}