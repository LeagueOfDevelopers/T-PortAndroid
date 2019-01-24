package com.lod.rtviwe.tport.search.populartrip

import com.lod.rtviwe.tport.data.MockTrips
import kotlinx.coroutines.*
import timber.log.Timber

class PopularTripNetworkDataSource : PopularTripDataSource {

    private val jobPopularTrips = Job()

    private val scopePopularTrips = CoroutineScope(Dispatchers.Main + jobPopularTrips)

    private val handlerPopularTrips = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting popular trips: $exception")
    }

    override fun setPopularTrips(callback: PopularTripDataSource.PopularTripCallback) {
        scopePopularTrips.launch(handlerPopularTrips) {
            callback.getPopularTrips(MockTrips.getItems())
        }
    }

    override fun clear() {
        jobPopularTrips.cancel()
    }
}