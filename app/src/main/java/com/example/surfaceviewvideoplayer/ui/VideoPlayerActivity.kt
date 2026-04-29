package com.example.surfaceviewvideoplayer.ui

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.surfaceviewvideoplayer.R
import com.example.surfaceviewvideoplayer.ui.viewmodel.VideoPlayerViewModel

class VideoPlayerActivity : AppCompatActivity(), SurfaceHolder.Callback {
    
    // === ГЛОБАЛЬНЫЕ ПЕРЕМЕННЫЕ ===
    
    /** SurfaceView из XML */
    private lateinit var surfaceView: SurfaceView
    
    /** Индикатор загрузки */
    private lateinit var progressBar: ProgressBar
    
    /** Поле для вывода лога */
    private lateinit var debugText: TextView
    
    /** MediaPlayer для воспроизведения видео */
    private var mediaPlayer: MediaPlayer? = null
    
    /** Холдер поверхности */
    private var surfaceHolder: SurfaceHolder? = null
    
    /** ViewModel с логикой воспроизведения */
    private lateinit var viewModel: VideoPlayerViewModel
    
    /** Список сообщений лога (последние 20) */
    private val logList = mutableListOf<String>()
    
    /** Handler для задержки перехода к следующему видео */
    private val handler = Handler(Looper.getMainLooper())
    
    /** Флаг, идёт ли переход к следующему видео */
    private var isTransitioning = false
    
    // === ЖИЗНЕННЫЙ ЦИКЛ ===
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        surfaceView = findViewById(R.id.surfaceView)
        progressBar = findViewById(R.id.progressBar)
        debugText = findViewById(R.id.debugText)        
        // Копирование лога по клику
        debugText.setOnClickListener {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = android.content.ClipData.newPlainText("log", debugText.text)
            clipboard.setPrimaryClip(clip)
            addLog("Лог скопирован")
        }
        addLog("UI найден")
        
        surfaceHolder = surfaceView.holder
        surfaceHolder?.addCallback(this)
        addLog("SurfaceHolder получен")
        
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return VideoPlayerViewModel(applicationContext) as T
            }
        }).get(VideoPlayerViewModel::class.java)
        addLog("ViewModel создана")
        
        viewModel.videoList.observe(this) { videos ->
            addLog("Загружено ${videos.size} видео")
            if (videos.isEmpty()) {
                addLog("Ошибка: список видео пуст")
            }
            progressBar.visibility = ProgressBar.GONE
        }
        
        viewModel.currentVideo.observe(this) { video ->
            if (!isTransitioning) {
                video?.let {
                    addLog("Начинаем ${it.videoIdentifier}")
                    startVideoPlayback(it.videoIdentifier)
                }
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        releaseMediaPlayer()
    }
    
    // === ЛОГИРОВАНИЕ ===
    
    /** Добавляет сообщение в лог */
    private fun addLog(msg: String) {
        logList.add(msg)
        while (logList.size > 20) logList.removeAt(0)
        runOnUiThread {
            debugText.text = logList.joinToString("\n")
        }
    }
    
    // === ВАЛИДАЦИЯ ВИДЕО ===
    
    /** Проверяет, можно ли воспроизвести видео */
    private fun isVideoValid(videoFileName: String): Boolean {
        val retriever = MediaMetadataRetriever()
        return try {
            val videoUri = Uri.parse("file:///android_asset/Videos/$videoFileName")
            retriever.setDataSource(applicationContext, videoUri)
            val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            duration != null && duration.toLong() > 0
        } catch (e: Exception) {
            false
        } finally {
            try { retriever.release() } catch (e: Exception) {}
        }
    }
    
    // === ВОСПРОИЗВЕДЕНИЕ ВИДЕО ===
    
    /** Запускает воспроизведение видео */
    private fun startVideoPlayback(videoFileName: String) {
        // Предотвращаем повторный вызов
        if (isTransitioning) {
            return
        }
        isTransitioning = true
        
        addLog("Проверка видео: $videoFileName")
        addLog("Имя файла: '$videoFileName'")
        progressBar.visibility = ProgressBar.VISIBLE
        releaseMediaPlayer()
        
        // ВАЛИДАЦИЯ: здесь проверяем видео
        //if (!isVideoValid(videoFileName)) {
        if (false) {
            addLog("Видео битое, пропускаем: $videoFileName")
            progressBar.visibility = ProgressBar.GONE
            
            handler.postDelayed({
                isTransitioning = false
                viewModel.playNextVideo()
            }, 100)
            return
        }
        
        addLog("Видео валидное, воспроизводим: $videoFileName")
        
        try {
            // Проверка существования файла
            val fileExists = try {
                assets.open("Videos/$videoFileName").close()
                true
            } catch (e: Exception) {
                false
            }
            
            if (!fileExists) {
                addLog("Файл не найден: $videoFileName")
                progressBar.visibility = ProgressBar.GONE
                handler.postDelayed({
                    isTransitioning = false
                    viewModel.playNextVideo()
                }, 100)
                return
            }
            
            mediaPlayer = MediaPlayer()
            
            val assetFileDescriptor = assets.openFd("Videos/$videoFileName")
            mediaPlayer?.setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
            assetFileDescriptor.close()
            addLog("DataSource установлен через AssetFileDescriptor")
            
            surfaceHolder?.let { mediaPlayer?.setDisplay(it) }
            
            mediaPlayer?.setOnPreparedListener {
                addLog("Видео готово")
                progressBar.visibility = ProgressBar.GONE
                mediaPlayer?.start()
                isTransitioning = false
            }
            
            mediaPlayer?.setOnCompletionListener {
                addLog("Видео завершено")
                handler.postDelayed({
                    isTransitioning = false
                    viewModel.playNextVideo()
                }, 100)
            }
            
            mediaPlayer?.setOnErrorListener { _, what, extra ->
                addLog("Ошибка MediaPlayer: код $what")
                progressBar.visibility = ProgressBar.GONE
                handler.postDelayed({
                    isTransitioning = false
                    viewModel.playNextVideo()
                }, 100)
                true
            }
            
            mediaPlayer?.prepareAsync()
            addLog("prepareAsync вызван")
            
        } catch (e: Exception) {
            addLog("Исключение: ${e.message}")
            progressBar.visibility = ProgressBar.GONE
            handler.postDelayed({
                isTransitioning = false
                viewModel.playNextVideo()
            }, 100)
        }
    }
    
    /** Освобождает ресурсы MediaPlayer */
    private fun releaseMediaPlayer() {
        try {
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            mediaPlayer = null
        }
    }
    
    // === КОЛБЭКИ SURFACEHOLDER ===
    
    override fun surfaceCreated(holder: SurfaceHolder) {
        surfaceHolder = holder
        addLog("Surface создана")
        mediaPlayer?.setDisplay(holder)
    }
    
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        addLog("Surface размер: ${width}x${height}")
    }
    
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        surfaceHolder = null
        addLog("Surface уничтожена")
        releaseMediaPlayer()
    }
}