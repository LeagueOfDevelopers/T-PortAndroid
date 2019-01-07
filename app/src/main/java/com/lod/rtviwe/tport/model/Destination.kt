package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.Duration

@Parcelize
data class Destination(
    val fromPlace: Place,
    val toPlace: Place,
    val duration: Duration
) : Parcelable