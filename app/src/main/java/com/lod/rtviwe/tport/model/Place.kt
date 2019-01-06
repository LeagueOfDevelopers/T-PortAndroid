package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
    var id: Long,
    var name: String,
    var latitude: Double?,
    var longitude: Double?
) : Parcelable