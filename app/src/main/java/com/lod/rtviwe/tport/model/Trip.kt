package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trip(
    val id: String,
    val destination: Destination,
    val routes: List<Route>,
    val cost: Double
) : Parcelable