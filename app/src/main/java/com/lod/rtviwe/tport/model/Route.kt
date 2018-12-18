package com.lod.rtviwe.tport.model

data class Route(
    var text: String,
    var type: RouteType,
    var cost: Int = 0,
    var destination: Destination,
    var isPaid: Boolean = false
)