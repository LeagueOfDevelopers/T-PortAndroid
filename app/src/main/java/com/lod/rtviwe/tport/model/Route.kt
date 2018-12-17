package com.lod.rtviwe.tport.model

data class Route(
    var text: String,
    var type: RouteType,
    var cost: Int = 0,
    var location: Location,
    var isPaid: Boolean = false
)