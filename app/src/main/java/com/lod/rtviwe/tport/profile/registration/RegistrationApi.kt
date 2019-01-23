package com.lod.rtviwe.tport.profile.registration

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface RegistrationApi {

    @POST("/login")
    fun sendPhoneNumber(
        @Body body: LoginRequest
    ): Deferred<Response<Void>>

    @PUT("/login")
    fun sendPhoneNumberWithCode(
        @Body body: LoginConfirmationRequest
    ): Deferred<Response<ResponseToken>>
}