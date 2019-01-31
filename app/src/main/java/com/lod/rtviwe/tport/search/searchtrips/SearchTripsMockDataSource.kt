package com.lod.rtviwe.tport.search.searchtrips

import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.model.Trip

class SearchTripsMockDataSource : SearchTripsDataSource {

    override fun findTrips(tripsRequest: SearchTripsRequest, callback: SearchTripsDataSource.SearchTripsCallback) {
        callback.getTrips(MockTrips.getItems())
    }

    override fun saveTrips(value: Pair<SearchTripsRequest, List<Trip>>) {}

    override fun clear() {}
}