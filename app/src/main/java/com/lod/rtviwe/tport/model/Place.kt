package com.lod.rtviwe.tport.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Place")
@Parcelize
data class Place(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_place")
    var id: Long,
    @ColumnInfo(name = "place_name")
    var name: String,
    @ColumnInfo(name = "place_latitude")
    var latitude: Double?,
    @ColumnInfo(name = "place_longitude")
    var longitude: Double?
) : Parcelable {

    @Ignore
    constructor(name: String, latitude: Double?, longitude: Double?) : this(0, name, latitude, longitude)
}