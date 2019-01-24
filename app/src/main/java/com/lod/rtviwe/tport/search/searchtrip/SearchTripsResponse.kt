package com.lod.rtviwe.tport.search.searchtrip

// TODO wait for new version from David
data class SearchTripsResponse(
    val id: String,
    val departureCity: CityResponse,
    val destinationCity: CityResponse,
    val routes: List<RouteResponse>,
    val duration: String,
    val cost: Int
)