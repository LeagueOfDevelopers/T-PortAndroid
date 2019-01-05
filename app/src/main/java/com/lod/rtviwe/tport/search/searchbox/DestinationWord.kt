package com.lod.rtviwe.tport.search.searchbox

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DestinationWord(
    var name: String = "",
    val code: String = ""
) : Parcelable