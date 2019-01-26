package com.lod.rtviwe.tport.search.searchtrip

import com.lod.rtviwe.tport.utils.CollectionJob
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class SearchTripsNetworkDataSource : SearchTripsDataSource, KoinComponent {

    private val collectionJob = CollectionJob()
    private val searchTripsApi: SearchTripsApi by inject()

    init {

        collectionJob.putJob(JOB_SEARCH_TRIPS)
    }

    override fun findTrips(tripsRequest: SearchTripsRequest, callback: SearchTripsDataSource.SearchTripsCallback) {
        val job = collectionJob.getJob(JOB_SEARCH_TRIPS)

        job?.let {
            it.scope.launch(it.handler) {
                // TODO replace it back
                val request = searchTripsApi.searchAsync(
                    /*tripsRequest.departureCityName*/"Москва",
                    /*tripsRequest.destinationCityName*/"Краснодар",
                    /*tripsRequest.departDate*/ "1.1.2019"
                ).await()
                val code = request.code()

                when (code) {
                    200 -> callback.getTrips(request.body()!!)
                    else -> Timber.e("Unknown error happened on David $code")
                }
//                callback.getTrips(MockTrips.getItems())
            }
        }
    }

    override fun clear() {
        collectionJob.clear()
    }

    companion object {

        private const val JOB_SEARCH_TRIPS = "SEARCH_TRIPS_JOB"
    }
}