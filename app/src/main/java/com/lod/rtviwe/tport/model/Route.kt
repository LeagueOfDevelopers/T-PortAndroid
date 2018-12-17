package com.lod.rtviwe.tport.model

import java.util.*

data class Route(
    var text: String,
    var type: RouteType,
    var cost: Int = 0,
    var location: Location,
    var arrivalDate: Date,
    var isPaid: Boolean = false
)