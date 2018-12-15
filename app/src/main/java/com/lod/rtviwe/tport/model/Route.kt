package com.lod.rtviwe.tport.model

data class Route(
    var text: String,
    var type: RouteType,
    var cost: Int,
    var placeFrom: String,
    var placeTo: String
)