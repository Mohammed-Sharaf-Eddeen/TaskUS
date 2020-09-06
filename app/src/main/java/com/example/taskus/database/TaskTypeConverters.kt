package com.example.taskus.database

import androidx.room.TypeConverter
import java.util.*

class TaskTypeConverters {
    @TypeConverter
    fun fromDeadline(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDeadline(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
}