package com.lod.rtviwe.tport.search.searchtrip

import com.lod.rtviwe.tport.data.MockTrips

class SearchTripsMockDataSource : SearchTripsDataSource {

    override fun findTrips(tripsRequest: SearchTripsRequest, callback: SearchTripsDataSource.SearchTripsCallback) {
        callback.getTrips(MockTrips.getItems())
    }

    override fun clear() {}
}