package com.example.surfaceviewvideoplayer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfaceviewvideoplayer.data.model.VideoItem
import com.example.surfaceviewvideoplayer.data.repository.VideoRepository
import kotlinx.coroutines.launch

/**
 * ViewModel для управления воспроизведением видео и состоянием UI
 * Реализует паттерн MVVM
 * @param context Контекст приложения (передается через фабрику)
 */
class VideoPlayerViewModel(private val context: Context) : ViewModel() {
    
    // Репозиторий для работы с данными
    private val repository = VideoRepository(context)
    
    // LiveData для текущего воспроизводимого видео
    private val _currentVideo = MutableLiveData<VideoItem>()
    val currentVideo: LiveData<VideoItem> = _currentVideo
    
    // LiveData для списка всех видео
    private val _videoList = MutableLiveData<List<VideoItem>>()
    val videoList: LiveData<List<VideoItem>> = _videoList
    
    // Индекс текущего видео в списке
    private var currentIndex = 0
    
    // Список видео (кэш)
    private var cachedVideoList: List<VideoItem> = emptyList()
    
    /**
     * Инициализация - загрузка списка видео при создании ViewModel
     */
    init {
        loadVideoList()
    }
    
    /**
     * Загрузка списка видео из репозитория
     * Использует viewModelScope для корутины
     */
    private fun loadVideoList() {
        viewModelScope.launch {
            val videos = repository.loadVideoList()
            cachedVideoList = videos
            _videoList.value = videos
            // Если список не пуст, начинаем с первого видео
            if (videos.isNotEmpty()) {
                playVideoByIndex(0)
            }
        }
    }
    
    /**
     * Воспроизведение видео по индексу в списке
     * @param index Индекс видео в списке
     */
    fun playVideoByIndex(index: Int) {
        if (cachedVideoList.isNotEmpty() && index < cachedVideoList.size) {
            currentIndex = index
            val video = cachedVideoList[currentIndex]
            _currentVideo.value = video
            // Сохраняем отчет о начале воспроизведения
            saveReportForCurrentVideo()
        }
    }
    
    /**
     * Воспроизведение следующего видео
     * При достижении конца списка - возвращается к началу (циклично)
     */
    fun playNextVideo() {
        val nextIndex = if (currentIndex + 1 < cachedVideoList.size) {
            currentIndex + 1
        } else {
            0 // Циклическое повторение: возвращаемся к первому видео
        }
        playVideoByIndex(nextIndex)
    }
    
    /**
     * Сохранение отчета о воспроизведении текущего видео
     */
    private fun saveReportForCurrentVideo() {
        val currentVideoValue = _currentVideo.value
        if (currentVideoValue != null) {
            viewModelScope.launch {
                repository.saveReport(currentVideoValue)
            }
        }
    }
}