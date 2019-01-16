package com.lod.rtviwe.tport.profile.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.network.register.LoginConfirmationRequest
import com.lod.rtviwe.tport.network.register.LoginRequest
import com.lod.rtviwe.tport.network.register.RegistrationApi
import com.lod.rtviwe.tport.network.register.ResponseToken
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import timber.log.Timber

class RegisterViewModel(app: Application) : AndroidViewModel(app), KoinComponent {

    private val jobSendCode = Job()
    private val jobSendPhone = Job()
    private val jobSendName = Job()

    private val scopeSendCode = CoroutineScope(Dispatchers.Main + jobSendCode)
    private val scopeSendPhone = CoroutineScope(Dispatchers.Main + jobSendPhone)
    private val scopeSendName = CoroutineScope(Dispatchers.Main + jobSendName)

    private val handlerSendCode = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while sending phone and code: $exception")
    }

    private val handlerSendPhone = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while sending phone: $exception")
    }

    private val handlerSendName = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while sending name: $exception")
    }

    private val registrationApi: RegistrationApi = get()

    override fun onCleared() {
        super.onCleared()
        jobSendCode.cancel()
        jobSendPhone.cancel()
        jobSendName.cancel()
    }

    fun login(onCodeCheckListener: CheckCodeCallback, loginConfirmationRequest: LoginConfirmationRequest) {
        scopeSendCode.launch(handlerSendCode) {
            val responseToken = checkCode(loginConfirmationRequest)
            if (responseToken != null) {
                onCodeCheckListener.pass(responseToken.token)
            } else {
                onCodeCheckListener.fail()
            }
        }
    }

    fun sendPhone(loginRequest: LoginRequest) {
        scopeSendPhone.launch(handlerSendPhone) {
            val request = registrationApi.sendPhoneNumber(loginRequest).await()
            val requestCode = request.code()

            when (requestCode) {
                200 -> Timber.v("Phone number has been send successfully")
                else -> Timber.e("Unknown error happened on David")
            }
        }
    }

    fun sendName(phoneNumber: String, name: String) {
        scopeSendName.launch(handlerSendName) {
            // TODO send name to David
        }
    }

    private suspend fun checkCode(loginConfirmationRequest: LoginConfirmationRequest): ResponseToken? {
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