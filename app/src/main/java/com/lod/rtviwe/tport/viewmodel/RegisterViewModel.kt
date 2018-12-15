package com.lod.rtviwe.tport.viewmodel

import android.app.Application
import android.util.Log
import com.lod.rtviwe.tport.network.RegistrationApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(app: Application) : BaseViewModel(app) {

    private val job = Job()
    private var code = 0
    private lateinit var registrationApi: RegistrationApi

    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun sendCodeToPhoneNumber(phoneNumber: Long, serviceApi: RegistrationApi) {
        registrationApi = serviceApi
        viewModelScope.launch {
            if (registrationApi.sendPhoneNumber(phoneNumber).await().body() == null) {
                Log.e("RegisterViewModel", "Error while getting code")
            } else {
                code = registrationApi.sendPhoneNumber(phoneNumber).await().body()!!
            }
        }
    }

    fun checkCode(code: Int) {
        viewModelScope.launch {
            val result = registrationApi.sendCode(code).await().isSuccessful

            if (result) {
                Log.v("RegisterViewModel", "Checking the code")
            } else {
                Log.e("RegisterViewModel", "Wrong code probably")
            }
        }
    }

    fun registerName() {
        viewModelScope.launch {

        }
    }
}