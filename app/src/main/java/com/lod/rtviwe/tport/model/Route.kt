package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Route(
    var id: Long,
    var tripId: Long,
    var vehicleName: String,
    var type: RouteType,
    var cost: Int = 0,
    var destination: Destination,
    var isPaid: Boolean = false
) : Parcelable