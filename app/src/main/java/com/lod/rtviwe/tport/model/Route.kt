package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Route(
    val id: String,
    val transport: Transport,
    val cost: Double,
    val destination: Destination,
    val departureDate: Date,
    val arrivalDate: Date,
    var isPaid: Boolean = false
) : Parcelable