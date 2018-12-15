package com.lod.rtviwe.tport.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface RegistrationApi {

    @POST("/registration")
    fun sendPhoneNumber(
        @Query("phone_number") phoneNumber: Long
    ): Deferred<Response<Int>>

    @POST("/registration")
    fun sendCode(
        @Query("code") code: Int
    ): Deferred<Response<Int>>
}