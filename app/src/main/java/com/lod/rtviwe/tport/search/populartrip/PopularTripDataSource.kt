package com.lod.rtviwe.tport.search.populartrip

import com.lod.rtviwe.tport.model.Trip

interface PopularTripDataSource {

    interface PopularTripCallback {

        fun getPopularTrips(trips: List<Trip>)
    }

    fun setPopularTrips(callback: PopularTripDataSource.PopularTripCallback)

    fun clear()
}