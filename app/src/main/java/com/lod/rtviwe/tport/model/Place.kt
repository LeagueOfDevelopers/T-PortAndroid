package com.lod.rtviwe.tport.model

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey

//@Entity(tableName = "Place")
data class Place(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_place")
    var id: Long,
    @ColumnInfo(name = "place_name")
    var name: String,
    @ColumnInfo(name = "place_latitude")
    var latitude: Double?,
    @ColumnInfo(name = "place_latitude")
    var longitude: Double?
) {

    @Ignore
    constructor(name: String, latitude: Double?, longitude: Double?) : this(0, name, latitude, longitude)
}