package com.lod.rtviwe.tport.profile.registration

import com.lod.rtviwe.tport.profile.registration.stepone.SendPhoneRequest
import com.lod.rtviwe.tport.profile.registration.stepthree.SendNameRequest
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeRequest

class RegistrationMockDataSource : RegistrationDataSource {

    override fun sendPhone(loginRequest: SendPhoneRequest) {}

    override fun sendName(nameRequest: SendNameRequest) {}

    override fun sendCode(sendCodeRequest: SendCodeRequest, callback: RegistrationDataSource.SendCodeCallback) {
        callback.success("mock token")
    }

    override fun clear() {}
}