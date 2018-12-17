package com.lod.rtviwe.tport.model

import java.util.*

data class Trip(
    var placeFrom: String,
    var placeTo: String,
    var cost: Int,
    var timeTravel: Date,
    var routes: List<Route> = listOf()
)