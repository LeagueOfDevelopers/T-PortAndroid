package com.lod.rtviwe.tport.network.register

data class LoginConfirmationRequest(
    val phone: String,
    val code: String
)