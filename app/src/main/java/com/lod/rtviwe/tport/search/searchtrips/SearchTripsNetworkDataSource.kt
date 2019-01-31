package com.lod.rtviwe.tport.search.searchtrips

import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.utils.CollectionJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class SearchTripsNetworkDataSource : SearchTripsDataSource, KoinComponent {

    private val collectionJob = CollectionJob()
    private val searchTripsApi by inject<SearchTripsApi>()

    init {

        collectionJob.putJob(JOB_SEARCH_TRIPS)
    }

    override fun findTrips(tripsRequest: SearchTripsRequest, callback: SearchTripsDataSource.SearchTripsCallback) {
        val job = collectionJob.getJob(JOB_SEARCH_TRIPS)

        job?.let {
            it.scope.launch(it.handler) {
                val request = searchTripsApi.searchAsync(
                    tripsRequest.departureCityName,
                    tripsRequest.destinationCityName,
                    tripsRequest.departDate
                ).await()
                val code = request.code()

                when (code) {
                    200 -> {
                        if (request.body() != null) {
                            callback.getTrips(request.body()!!)
                        } else {
                            Timber.e("Body is empty from David $code")
                        }
                    }
                    else -> Timber.e("Unknown error happened on David $code")
                }
            }
        }
    }

    override fun saveTrips(value: Pair<SearchTripsRequest, List<Trip>>) {
        // TODO save in persistent
    }

    override fun clear() {
        collectionJob.clear()
    }

    companion object {

        private const val JOB_SEARCH_TRIPS = "SEARCH_TRIPS_JOB"
    }
}