package com.lod.rtviwe.tport.profile.registration

import com.lod.rtviwe.tport.profile.registration.stepone.SendPhoneRequest
import com.lod.rtviwe.tport.profile.registration.steptwo.ResponseToken
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface RegistrationApi {

    @POST("/sendCode")
    fun sendPhoneNumberAsync(
        @Body body: SendPhoneRequest
    ): Deferred<Response<Void>>

    @PUT("/sendCode")
    fun sendPhoneNumberWithCodeAsync(
        @Body body: SendCodeRequest
    ): Deferred<Response<ResponseToken>>
}