package com.lod.rtviwe.tport.profile.registration.steptwo

interface SendCodeDataSource {

    interface SendCodeCallback {

        fun success(token: String)

        fun fail()
    }

    fun sendCode(sendCodeRequest: SendCodeRequest, callback: SendCodeCallback)

    fun clear()
}