package com.lod.rtviwe.tport.profile.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.network.register.LoginConfirmationRequest
import com.lod.rtviwe.tport.network.register.RegistrationApi
import com.lod.rtviwe.tport.network.register.ResponseToken
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import timber.log.Timber

class RegisterViewModel(app: Application) : AndroidViewModel(app), KoinComponent {

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

    private val registrationApi: RegistrationApi = get()

    override fun onCleared() {
        super.onCleared()
        jobSendCode.cancel()
        jobSendName.cancel()
    }

    fun login(onCodeCheckListener: CheckCodeCallback, loginConfirmationRequest: LoginConfirmationRequest) {
        jobSendCode.cancelChildren()
        scopeSendCode.launch(handlerSendCode) {
            val responseToken = checkCode(loginConfirmationRequest)
            if (responseToken != null) {
                onCodeCheckListener.pass(responseToken.token)
            } else {
                onCodeCheckListener.fail()
            }
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