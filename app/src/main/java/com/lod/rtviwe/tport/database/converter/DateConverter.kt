package com.lod.rtviwe.tport.database.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(dateLong: Long) = Date(dateLong)

    @TypeConverter
    fun toLong(date: Date) = date.time
}