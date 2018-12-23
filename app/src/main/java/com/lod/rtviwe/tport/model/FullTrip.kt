package com.lod.rtviwe.tport.model

import androidx.room.Embedded
import androidx.room.Relation

data class FullTrip(
    @Embedded
    var trip: Trip,
    @Relation(parentColumn = "id_trip", entityColumn = "id_to_trip")
    var routes: List<Route>?
)