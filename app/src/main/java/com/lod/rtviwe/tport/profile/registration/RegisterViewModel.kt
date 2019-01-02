package com.lod.rtviwe.tport.profile.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.network.LoginConfirmationRequest
import com.lod.rtviwe.tport.network.RegistrationApi
import com.lod.rtviwe.tport.network.ResponseToken
import kotlinx.coroutines.*
import timber.log.Timber

class RegisterViewModel(app: Application) : AndroidViewModel(app) {

    private val jobSendCode = Job()
    private val jobSendName = Job()

    private val scopeSendCode = CoroutineScope(Dispatchers.Main + jobSendCode)
    private val scopeSendName = CoroutineScope(Dispatchers.Main + jobSendName)

    private val handlerSendCode = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while sending phone number and code: $exception")
    }

    private val handlerSendName = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while sending name: $exception")
    }

    private lateinit var registrationApi: RegistrationApi
    private lateinit var loginConfirmationRequest: LoginConfirmationRequest

    override fun onCleared() {
        super.onCleared()
        jobSendCode.cancel()
        jobSendName.cancel()
    }

    fun login(
        registrationApi: RegistrationApi,
        onCodeCheckListener: CheckCodeCallback,
        loginConfirmationRequest: LoginConfirmationRequest
    ) {
        this.registrationApi = registrationApi
        this.loginConfirmationRequest = loginConfirmationRequest

        jobSendCode.cancelChildren()
        scopeSendCode.launch(handlerSendCode) {
            val responseToken = checkCode()
            if (responseToken != null) {
                onCodeCheckListener.pass(responseToken.token)
            } else {
                onCodeCheckListener.fail()
            }
        }
    }

    private suspend fun checkCode(): ResponseToken? {
        val request = registrationApi.sendPhoneNumberWithCode(loginConfirmationRequest).await()
        val requestCode = request.code()

        when (requestCode) {
            200 -> {
                Timber.v("Right code")
                return request.body()
            }
            400 -> Timber.e("Wrong code")
            else -> Timber.e("Unknown error happened on David")
        }

        return null
    }
}