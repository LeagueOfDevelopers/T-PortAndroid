package com.lod.rtviwe.tport.database.converter

import androidx.room.TypeConverter
import com.lod.rtviwe.tport.model.RouteType

class RouteTypeConverter {

    @TypeConverter
    fun toInt(routeType: RouteType) = routeType.ordinal

    @TypeConverter
    fun toRouteType(id: Int) = RouteType.values().first { it.ordinal == id }
}