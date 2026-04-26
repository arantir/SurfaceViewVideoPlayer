package com.example.surfaceviewvideoplayer.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reports")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    
    @ColumnInfo(name = "video_id")
    val videoId: Int,
    
    @ColumnInfo(name = "video_name")
    val videoName: String,
    
    @ColumnInfo(name = "start_time")
    val startTime: Long
)
