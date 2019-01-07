package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

@Parcelize
data class Route(
    val id: String,
    val transport: Transport,
    val cost: Double,
    val destination: Destination,
    val departureDate: DateTime,
    val arrivalDate: DateTime,
    var isPaid: Boolean = false
) : Parcelable