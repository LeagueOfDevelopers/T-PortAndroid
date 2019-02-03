package com.lod.rtviwe.tport.profile.registration

import com.lod.rtviwe.tport.profile.registration.stepone.SendPhoneRequest
import com.lod.rtviwe.tport.profile.registration.stepthree.SendNameRequest
import com.lod.rtviwe.tport.profile.registration.steptwo.ResponseToken
import com.lod.rtviwe.tport.profile.registration.steptwo.SendCodeRequest
import com.lod.rtviwe.tport.utils.AuthService
import com.lod.rtviwe.tport.utils.CollectionJob
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class RegistrationNetworkDataSource : RegistrationDataSource, KoinComponent {

    private val authService by inject<AuthService>()
    private val registrationApi by inject<RegistrationApi>()

    private val collectionJob = CollectionJob()

    init {

        collectionJob.putJobs(JOB_SEND_PHONE, JOB_SEND_CODE, JOB_SEND_NAME)
    }

    override fun sendPhone(loginRequest: SendPhoneRequest) {
        val job = collectionJob.getJob(JOB_SEND_PHONE)

        job?.let {
            it.scope.launch(it.handler) {
                val request = registrationApi.sendPhoneNumberAsync(loginRequest).await()
                val requestCode = request.code()

                when (requestCode) {
                    200 -> Timber.v("Phone number has been send successfully")
                    else -> Timber.e("Unknown error happened on David")
                }
            }
        }
    }

    override fun sendName(nameRequest: SendNameRequest) {
        val job = collectionJob.getJob(JOB_SEND_NAME)

        job?.let {
            it.scope.launch(it.handler) {
                // TODO send name to David
                authService.putName(nameRequest.name) // TODO if success
            }
        }
    }

    override fun sendCode(sendCodeRequest: SendCodeRequest, callback: RegistrationDataSource.SendCodeCallback) {
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

    override fun clear() {
        collectionJob.clear()
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

    companion object {

        private const val JOB_SEND_PHONE = "SEND_CODE_JOB"
        private const val JOB_SEND_CODE = "SEND_CODE_JOB"
        private const val JOB_SEND_NAME = "SEND_NAME_JOB"
    }
}