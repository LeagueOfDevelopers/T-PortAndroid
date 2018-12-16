package com.lod.rtviwe.tport.viewmodel

import android.app.Application
import com.lod.rtviwe.tport.network.RegistrationApi
import kotlinx.coroutines.*
import timber.log.Timber

class RegisterViewModel(app: Application) : BaseViewModel(app) {

    private val jobSendCode = Job()
    private val jobCheckCode = Job()
    private val jobRegisterName = Job()

    private val scopeSendCode = CoroutineScope(Dispatchers.IO + jobSendCode)
    private val scopeCheckCode = CoroutineScope(Dispatchers.IO + jobCheckCode)
    private val scopeRegisterName = CoroutineScope(Dispatchers.IO + jobRegisterName)

    private lateinit var registrationApi: RegistrationApi

    private var code = 0

    override fun onCleared() {
        super.onCleared()
        jobCheckCode.cancel()
        jobSendCode.cancel()
        jobRegisterName.cancel()
    }

    fun sendCodeToPhoneNumber(phoneNumber: Long, api: RegistrationApi) {
        registrationApi = api
        jobSendCode.cancelChildren()
        scopeSendCode.launch {
            if (registrationApi.sendPhoneNumber(phoneNumber).await().body() == null) {
                Timber.e("Error while getting code")
            } else {
                code = registrationApi.sendPhoneNumber(phoneNumber).await().body()!!
            }

            for (i in 1..100) {
                delay(2000)
                Timber.v("sendCodeToPhoneNumber $i")
            }
        }
    }

    fun checkCode(code: Int) {
        jobCheckCode.cancelChildren()
        scopeCheckCode.launch {
            val result = registrationApi.sendCode(code).await().isSuccessful

            if (result) {
                Timber.v("Right code")
            } else {
                Timber.tag("RegisterViewModel").e("Wrong code probably")
            }

            for (i in 1..100) {
                delay(2000)
                Timber.v("checkCode $i")
            }
        }
    }

    fun registerName() {
        scopeRegisterName.launch {

        }
    }
}