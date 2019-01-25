package com.lod.rtviwe.tport.profile.registration

import com.lod.rtviwe.tport.profile.registration.stepone.SendPhoneDataSource
import com.lod.rtviwe.tport.profile.registration.stepthree.SendNameDataSource
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeDataSource

interface RegistrationDataSource {

    fun getSendPhoneDataSource(): SendPhoneDataSource

    fun getNameDataSource(): SendNameDataSource

    fun getCodeDataSource(): SendCodeDataSource

    fun clear()
}