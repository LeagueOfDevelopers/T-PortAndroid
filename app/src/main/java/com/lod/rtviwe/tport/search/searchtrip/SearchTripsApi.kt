package com.lod.rtviwe.tport.search.searchtrip

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchTripsApi {

    @POST("/search")
    fun search(
        @Body tripsRequest: TripsRequest
    ): Deferred<Response<TripsResponse>>
}