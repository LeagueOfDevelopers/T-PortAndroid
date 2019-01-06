package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Destination(
    val fromPlace: Place,
    val toPlace: Place,
    val duration: /*Duration*/ Date
) : Parcelable