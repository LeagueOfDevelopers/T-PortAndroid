package com.lod.rtviwe.tport.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.utils.AuthService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ProfileViewModel(app: Application) : AndroidViewModel(app), KoinComponent {

    private val authService by inject<AuthService>()

    fun getUserName() = authService.getName()
}