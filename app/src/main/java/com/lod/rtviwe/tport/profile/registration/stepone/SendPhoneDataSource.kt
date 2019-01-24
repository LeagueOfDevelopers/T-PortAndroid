package com.lod.rtviwe.tport.profile.registration.stepone

interface SendPhoneDataSource {

    fun sendPhone(loginRequest: SendPhoneRequest)

    fun clear()
}