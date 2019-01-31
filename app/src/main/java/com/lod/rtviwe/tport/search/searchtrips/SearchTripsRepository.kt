package com.lod.rtviwe.tport.search.searchtrips

import com.lod.rtviwe.tport.model.Trip

class SearchTripsRepository(
    private val searchTripsDataSource: SearchTripsDataSource
) : SearchTripsDataSource {

    // is it effective?
    private val cache = hashMapOf<SearchTripsRequest, List<Trip>>()

    override fun findTrips(tripsRequest: SearchTripsRequest, callback: SearchTripsDataSource.SearchTripsCallback) {
        val suggestions = cache[tripsRequest]
        if (suggestions != null) {
            callback.getTrips(suggestions)
        } else {
            searchTripsDataSource.findTrips(tripsRequest, callback)
        }
    }

    override fun saveTrips(value: Pair<SearchTripsRequest, List<Trip>>) {
        cache[value.first] = value.second
        searchTripsDataSource.saveTrips(value)
    }

    override fun clear() {
        searchTripsDataSource.clear()
    }
}