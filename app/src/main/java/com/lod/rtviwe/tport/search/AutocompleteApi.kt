package com.lod.rtviwe.tport.search

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AutocompleteApi {

    @POST("/autocompleteSuggestions/api/4_1/rs/suggest/address/")
    fun getAutocomplete(
        @Body body: AutocompleteRequest
    ): Deferred<Response<AutocompleteResponse>>
}