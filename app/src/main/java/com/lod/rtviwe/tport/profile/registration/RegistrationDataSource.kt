package com.lod.rtviwe.tport.profile.registration

import com.lod.rtviwe.tport.profile.registration.stepone.SendPhoneRequest
import com.lod.rtviwe.tport.profile.registration.stepthree.SendNameRequest
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeRequest

interface RegistrationDataSource {

    interface SendCodeCallback {

        fun success(token: String)

        fun fail()
    }

    fun sendPhone(loginRequest: SendPhoneRequest)

    fun sendName(nameRequest: SendNameRequest)

    fun sendCode(sendCodeRequest: SendCodeRequest, callback: SendCodeCallback)

    fun clear()
}