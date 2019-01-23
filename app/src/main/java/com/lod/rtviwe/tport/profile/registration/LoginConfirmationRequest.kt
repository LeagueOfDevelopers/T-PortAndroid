package com.lod.rtviwe.tport.profile.registration

data class LoginConfirmationRequest(
    val phone: String,
    val code: String
)