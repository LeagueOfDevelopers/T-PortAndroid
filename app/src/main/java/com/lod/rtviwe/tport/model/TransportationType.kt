package com.lod.rtviwe.tport.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class TransportationType : Parcelable {
    @SerializedName("0")
    TAXI,
    @SerializedName("1")
    AIRPLANE,
    @SerializedName("2")
    TRAIN,
    @SerializedName("3")
    SHIP,
    @SerializedName("4")
    FOOT
}