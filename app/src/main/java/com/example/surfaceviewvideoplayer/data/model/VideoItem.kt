package com.example.surfaceviewvideoplayer.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class для представления видео элемента из JSON
 * @property videoId Уникальный идентификатор видео
 * @property videoIdentifier Имя файла видео (например "video1.mp4")
 * @property orderNumber Порядковый номер в плейлисте
 */
data class VideoItem(
    @SerializedName("VideoId")
    val videoId: Int,
    
    @SerializedName("VideoIdentifier")
    val videoIdentifier: String,
    
    @SerializedName("OrderNumber")
    val orderNumber: Int
)