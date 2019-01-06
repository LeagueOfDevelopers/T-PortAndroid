package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Trip(
    var id: Long,
    var placeFrom: Place,
    var placeTo: Place,
    var cost: Int,
    var timeTravel: Date
) : Parcelable