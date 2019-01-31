package com.lod.rtviwe.tport.search.populartrip

import com.lod.rtviwe.tport.data.MockTrips

class PopularTripMockDataSource : PopularTripDataSource {

    override fun setPopularTrips(callback: PopularTripDataSource.PopularTripCallback) {
        callback.getPopularTrips(MockTrips.getItems())
    }

    override fun clear() {}
}