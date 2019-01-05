package com.lod.rtviwe.tport.network.searchTrips

data class TripsRequest(
    val departureCityCode: String,
    val destinationCityCode: String,
    val departDate: String
)