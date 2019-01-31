package com.lod.rtviwe.tport.search.searchtrips

data class SearchTripsRequest(
    val departureCityName: String,
    val destinationCityName: String,
    val departDate: String
)