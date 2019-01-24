package com.lod.rtviwe.tport.search.populartrip

import androidx.lifecycle.LifecycleOwner
import com.lod.rtviwe.tport.model.Trip

interface PopularTripDataSource {

    interface PopularTripCallback {

        fun getPopularTrips(trips: List<Trip>)
    }

    fun setPopularTrips(lifecycleOwner: LifecycleOwner, callback: PopularTripDataSource.PopularTripCallback)

    fun clear()
}