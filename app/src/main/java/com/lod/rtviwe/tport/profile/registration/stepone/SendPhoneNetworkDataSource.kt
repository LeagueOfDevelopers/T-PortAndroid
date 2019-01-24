package com.lod.rtviwe.tport.profile.registration.stepone

import com.lod.rtviwe.tport.profile.registration.RegistrationApi
import com.lod.rtviwe.tport.utils.CollectionJob
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class SendPhoneNetworkDataSource : SendPhoneDataSource, KoinComponent {

    private val collectionJob = CollectionJob()

    init {

        collectionJob.putJob(JOB_SEND_PHONE)
    }

    private val registrationApi by inject<RegistrationApi>()

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

    override fun clear() {
        collectionJob.clear()
    }

    companion object {

        private const val JOB_SEND_PHONE = "SEND_CODE_JOB"
    }
}