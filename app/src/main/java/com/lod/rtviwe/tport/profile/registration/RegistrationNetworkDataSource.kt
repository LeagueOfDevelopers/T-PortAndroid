package com.lod.rtviwe.tport.profile.registration

import com.lod.rtviwe.tport.profile.registration.stepone.SendPhoneNetworkDataSource
import com.lod.rtviwe.tport.profile.registration.stepthree.SendNameNetworkDataSource
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeNetworkDataSource

class RegistrationNetworkDataSource(
    private val sendPhoneDataSource: SendPhoneNetworkDataSource,
    private val sendNameDataSource: SendNameNetworkDataSource,
    private val sendCodeDataSource: SendCodeNetworkDataSource
) : RegistrationDataSource {

    override fun getSendPhoneDataSource() = sendPhoneDataSource

    override fun getNameDataSource() = sendNameDataSource

    override fun getCodeDataSource() = sendCodeDataSource

    override fun clear() {
        sendPhoneDataSource.clear()
        sendNameDataSource.clear()
        sendCodeDataSource.clear()
    }
}