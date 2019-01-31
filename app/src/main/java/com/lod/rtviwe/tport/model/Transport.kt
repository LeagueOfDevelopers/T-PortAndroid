package com.lod.rtviwe.tport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transport(
    val name: String,
    val type: TransportationType?
) : Parcelable