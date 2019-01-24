package com.lod.rtviwe.tport.profile.registration.stepthree

import com.lod.rtviwe.tport.profile.registration.RegistrationApi
import com.lod.rtviwe.tport.utils.CollectionJob
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SendNameNetworkDataSource : SendNameDataSource, KoinComponent {

    private val collectionJob = CollectionJob()

    init {

        collectionJob.putJob(JOB_SEND_NAME)
    }

    private val registrationApi by inject<RegistrationApi>()

    override fun sendName(nameRequest: SendNameRequest) {
        val job = collectionJob.getJob(JOB_SEND_NAME)

        job?.let {
            it.scope.launch(it.handler) {
                // TODO send name to David
            }
        }
    }

    override fun clear() {
        collectionJob.clear()
    }

    companion object {

        private const val JOB_SEND_NAME = "SEND_CODE_JOB"
    }
}