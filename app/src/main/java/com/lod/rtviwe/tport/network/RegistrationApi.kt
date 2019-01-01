package com.lod.rtviwe.tport.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface RegistrationApi {

    @POST("/login")
    fun sendPhoneNumber(
        @Query("phone") phoneNumber: String
    ): Deferred<Response<Int>>

    @PUT("/login")
    fun sendPhoneNumberWithCode(
        @Body body: String
    ): Deferred<Response<Int>>
}