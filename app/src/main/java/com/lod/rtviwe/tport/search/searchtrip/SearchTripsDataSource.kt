package com.lod.rtviwe.tport.search.searchtrip

import com.lod.rtviwe.tport.model.Trip

interface SearchTripsDataSource {

    interface SearchTripsCallback {

        fun getTrips(trips: List<Trip>)
    }

    fun findTrips(tripsRequest: SearchTripsRequest, callback: SearchTripsCallback)

    fun clear()
}