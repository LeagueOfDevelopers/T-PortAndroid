package com.lod.rtviwe.tport.network.autocomplete

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AutocompleteApi {

    @POST("/suggest/address")
    @Headers("Authorization: Token 1cf94e840097454101fdbb0a52ce1ec7ee7ce6ea")
    fun getAutocomplete(
        @Body body: DadataRequest
    ): Deferred<Response<Void>>
}