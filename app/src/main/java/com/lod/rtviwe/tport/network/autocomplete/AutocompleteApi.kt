package com.lod.rtviwe.tport.network.autocomplete

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AutocompleteApi {

    @GET("/places2")
    fun getAutocomplete(
        @Query("term") text: String,
        @Query("locale") locale: String
    ): Deferred<Response<Array<AutocompleteSuggestions>>>
}