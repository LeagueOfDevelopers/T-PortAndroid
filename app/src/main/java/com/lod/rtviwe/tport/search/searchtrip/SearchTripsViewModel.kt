package com.lod.rtviwe.tport.search.searchtrip

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.searchtrip.items.TripItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import org.koin.standalone.KoinComponent

class SearchTripsViewModel(
    private val app: Application,
    private val searchTripsDataSource: SearchTripsDataSource
) : AndroidViewModel(app), KoinComponent {

    override fun onCleared() {
        super.onCleared()
        searchTripsDataSource.clear()
    }

    fun observeAdapter(searchTripsAdapter: GroupAdapter<ViewHolder>, tripsRequest: SearchTripsRequest) {
        searchTripsDataSource.findTrips(tripsRequest, object : SearchTripsDataSource.SearchTripsCallback {
            override fun getTrips(trips: List<Trip>) {
                searchTripsAdapter.addAll(trips.map(::TripItem))
            }
        })
    }
}