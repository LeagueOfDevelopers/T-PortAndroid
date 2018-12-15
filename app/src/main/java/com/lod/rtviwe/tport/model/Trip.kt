package com.lod.rtviwe.tport.model

import java.util.*

data class Trip(
    var placeFrom: String,
    var placeTo: String,
    var backgroundUrl: String,
    var cost: Int,
    var timeTravel: Date,
    var items: List<Route>
)