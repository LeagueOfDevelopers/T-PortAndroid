package com.lod.rtviwe.tport.profile.registration.steptwo

import com.lod.rtviwe.tport.profile.registration.RegistrationApi
import com.lod.rtviwe.tport.utils.CollectionJob
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class SendCodeNetworkDataSource : SendCodeDataSource, KoinComponent {

    private val collectionJob = CollectionJob()

    init {

        collectionJob.putJob(JOB_SEND_CODE)
    }

    private val registrationApi by inject<RegistrationApi>()

    override fun sendCode(sendCodeRequest: SendCodeRequest, callback: SendCodeDataSource.SendCodeCallback) {
        val job = collectionJob.getJob(JOB_SEND_CODE)

        job?.let {
            it.scope.launch(it.handler) {
                val responseToken = checkCode(sendCodeRequest)
                if (responseToken != null) {
                    callback.success(responseToken.token)
                } else {
                    callback.fail()
                }
            }
        }
    }

    private suspend fun checkCode(loginConfirmationRequest: SendCodeRequest): ResponseToken? {
        val request = registrationApi.sendPhoneNumberWithCodeAsync(loginConfirmationRequest).await()
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

    override fun clear() {
        collectionJob.clear()
    }

    companion object {

        private const val JOB_SEND_CODE = "SEND_CODE_JOB"
    }
}