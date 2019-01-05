package com.lod.rtviwe.tport.network.searchTrips

data class Route(
    val arrivalDate: String,
    val cost: Int,
    val departureDate: String,
    val destinationCode: String,
    val id: String,
    val type: Int
)