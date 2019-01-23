package com.lod.rtviwe.tport.search.searchtrip

data class TripsRequest(
    val departureCityName: String,
    val destinationCityName: String,
    val departDate: String
)