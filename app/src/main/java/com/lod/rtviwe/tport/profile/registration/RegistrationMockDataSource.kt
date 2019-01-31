package com.lod.rtviwe.tport.profile.registration

import com.lod.rtviwe.tport.profile.registration.stepone.SendPhoneRequest
import com.lod.rtviwe.tport.profile.registration.stepthree.SendNameRequest
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeRequest
import com.lod.rtviwe.tport.utils.AuthService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class RegistrationMockDataSource : RegistrationDataSource, KoinComponent {

    private val authService by inject<AuthService>()

    override fun sendPhone(loginRequest: SendPhoneRequest) {}

    override fun sendName(nameRequest: SendNameRequest) {
        authService.putName(nameRequest.name)
    }

    override fun sendCode(sendCodeRequest: SendCodeRequest, callback: RegistrationDataSource.SendCodeCallback) {
        callback.success("mock token")
    }

    override fun clear() {}
}