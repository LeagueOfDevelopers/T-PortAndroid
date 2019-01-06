package com.lod.rtviwe.tport.network.searchTrips

data class TripsResponse(
    val cost: Int,
    val departureCityCode: String,
    val destinationCityCode: String,
    val duration: String,
    val id: String,
    val routes: List<Route>
)