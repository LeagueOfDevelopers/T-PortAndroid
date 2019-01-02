package com.lod.rtviwe.tport.network

data class LoginConfirmationRequest(
    val phone: String,
    val code: String
)