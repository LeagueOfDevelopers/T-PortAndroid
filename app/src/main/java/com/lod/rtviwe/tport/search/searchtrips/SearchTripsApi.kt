package com.lod.rtviwe.tport.search.searchtrips

import com.lod.rtviwe.tport.model.Trip
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchTripsApi {

    @GET("/trips")
    fun searchAsync(
        @Query("DepartureCityName") departure: String,
        @Query("DestinationCityName") destination: String,
        @Query("DepartDate") date: String
    ): Deferred<Response<List<Trip>>>
}