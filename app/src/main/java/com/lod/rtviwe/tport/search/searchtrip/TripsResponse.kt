package com.lod.rtviwe.tport.search.searchtrip

data class TripsResponse(
    val id: String,
    val departureCity: CityResponse,
    val destinationCity: CityResponse,
    val routes: List<RouteResponse>,
    val duration: String,
    val cost: Int
)