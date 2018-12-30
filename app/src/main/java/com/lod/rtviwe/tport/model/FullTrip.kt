package com.lod.rtviwe.tport.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FullTrip(
    @Embedded
    var trip: Trip,
    @Relation(parentColumn = "id_trip", entityColumn = "id_to_trip")
    var routes: List<Route>?
) : Parcelable