package com.lod.rtviwe.tport.profile.registration.stepthree

interface SendNameDataSource {

    fun sendName(nameRequest: SendNameRequest)

    fun clear()
}