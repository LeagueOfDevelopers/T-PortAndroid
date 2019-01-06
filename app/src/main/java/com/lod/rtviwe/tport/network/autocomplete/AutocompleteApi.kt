package com.lod.rtviwe.tport.network.autocomplete

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AutocompleteApi {

    @POST("/suggestions/api/4_1/rs/suggest/address/")
    fun getAutocomplete(
        @Body body: AutocompleteRequest
    ): Deferred<Response<AutocompleteResponse>>
}