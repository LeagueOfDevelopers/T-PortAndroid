package com.lod.rtviwe.tport.network.searchTrips

data class TripsRequest(
    val departureCityName: String,
    val destinationCityName: String,
    val departDate: String
)