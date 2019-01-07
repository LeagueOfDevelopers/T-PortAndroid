package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Destination(
    val fromPlace: Place,
    val toPlace: Place
) : Parcelable