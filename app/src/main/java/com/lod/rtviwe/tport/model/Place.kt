package com.lod.rtviwe.tport.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Place")
@Parcelize
data class Place(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var latitude: Double?,
    var longitude: Double?
) : Parcelable {

    @Ignore
    constructor(name: String, latitude: Double?, longitude: Double?) : this(0, name, latitude, longitude)
}