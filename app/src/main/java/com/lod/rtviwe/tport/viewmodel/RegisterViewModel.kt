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

    private val handlerSendCode = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting code: $exception")
    }

    private val handlerCheckCode = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while checking code: $exception")
    }

    private val handlerRegisterName = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while registering name: $exception")
    }

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
        scopeSendCode.launch(handlerSendCode) {
            if (registrationApi.sendPhoneNumber(phoneNumber).await().body() == null) {
                Timber.e("Error while getting code")
            } else {
                code = registrationApi.sendPhoneNumber(phoneNumber).await().body()!!
            }
        }
    }

    fun checkCode(codeString: String) {
        try {
            code = codeString.toInt()
        } catch (e: ClassCastException) {
            Timber.e("Code cannot be casted to int")
            // TODO показать ошибку пользователю
            return
        }

        jobCheckCode.cancelChildren()
        scopeCheckCode.launch(handlerCheckCode) {
            val result = registrationApi.sendCode(code).await().isSuccessful

            if (result) {
                Timber.v("Right code")
            } else {
                Timber.e("Wrong code probably")
            }
        }
    }

    fun registerName() {
        scopeRegisterName.launch(handlerRegisterName) {

        }
    }
}