package com.lod.rtviwe.tport.profile.registration.steptwo

data class SendCodeRequest(
    val phone: String,
    val code: String
)