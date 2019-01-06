package com.lod.rtviwe.tport.network.searchTrips

data class TripsResponse(
    val id: String,
    val departureCity: City,
    val destinationCity: City,
    val routes: List<Route>,
    val duration: String,
    val cost: Int
)