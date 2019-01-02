package com.lod.rtviwe.tport.profile.registration

interface CheckCodeCallback {

    fun pass(token: String)

    fun fail()
}