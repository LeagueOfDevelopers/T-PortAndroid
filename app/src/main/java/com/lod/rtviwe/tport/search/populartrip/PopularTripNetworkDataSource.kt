package com.lod.rtviwe.tport.search.populartrip

import com.lod.rtviwe.tport.utils.CollectionJob
import kotlinx.coroutines.launch

class PopularTripNetworkDataSource : PopularTripDataSource {

    private val collectionJob = CollectionJob()

    init {

        collectionJob.putJobs(JOB_POPULAR_TRIPS)
    }

    override fun setPopularTrips(callback: PopularTripDataSource.PopularTripCallback) {
        val job = collectionJob.getJob(JOB_POPULAR_TRIPS)

        job?.let {
            it.scope.launch(it.handler) {
                // TODO
            }
        }
    }

    override fun clear() {
        collectionJob.clear()
    }

    companion object {

        private const val JOB_POPULAR_TRIPS = "POPULAR_TRIPS_JOB"
    }
}