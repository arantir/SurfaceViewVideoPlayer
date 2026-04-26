package com.example.surfaceviewvideoplayer.ui

import android.os.Bundle
import android.view.SurfaceView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.surfaceviewvideoplayer.R
import com.example.surfaceviewvideoplayer.ui.viewmodel.VideoPlayerViewModel

class VideoPlayerActivity : AppCompatActivity() {
    
    private lateinit var surfaceView: SurfaceView
    private lateinit var progressBar: ProgressBar
    private lateinit var debugText: TextView
    private lateinit var viewModel: VideoPlayerViewModel
    private val logList = mutableListOf<String>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        surfaceView = findViewById(R.id.surfaceView)
        progressBar = findViewById(R.id.progressBar)
        debugText = findViewById(R.id.debugText)
        
        addLog("Приложение запущено")
        addLog("SurfaceView добавлен, фон красный")
        
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return VideoPlayerViewModel(applicationContext) as T
            }
        }).get(VideoPlayerViewModel::class.java)
        
        addLog("ViewModel создана")
        
        viewModel.videoList.observe(this) { videos ->
            addLog("Загружено видео: ${videos.size}")
            videos.forEach { video ->
                addLog(video.videoIdentifier)
            }
            progressBar.visibility = ProgressBar.GONE
        }
        
        viewModel.currentVideo.observe(this) { video ->
            video?.let {
                addLog("Сейчас: ${it.videoIdentifier}")
            }
        }
    }
    
    private fun addLog(msg: String) {
        logList.add(msg)
        while (logList.size > 20) {
            logList.removeAt(0)
        }
        runOnUiThread {
            debugText.text = logList.joinToString("\n")
        }
    }
}