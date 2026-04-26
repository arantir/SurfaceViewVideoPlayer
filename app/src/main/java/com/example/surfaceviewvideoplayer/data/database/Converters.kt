package com.example.surfaceviewvideoplayer.data.database

import androidx.room.TypeConverter
import java.util.Date

/**
 * Конвертер для преобразования типов в Room
 * Преобразует Date в Long и обратно для сохранения в SQLite
 */
class Converters {
    
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
