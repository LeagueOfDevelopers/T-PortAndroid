package com.lod.rtviwe.tport.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

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