package com.lod.rtviwe.tport.search.searchtrip

data class RouteResponse(
    val arrivalDate: String,
    val cost: Int,
    val departureDate: String,
    val destinationCode: String,
    val id: String,
    val type: Int
)