package com.lod.rtviwe.tport.search.searchtrips

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.searchtrips.items.TripItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import org.koin.standalone.KoinComponent

class SearchTripsViewModel(
    private val app: Application,
    private val searchTripsRepository: SearchTripsRepository
) : AndroidViewModel(app), KoinComponent {

    override fun onCleared() {
        super.onCleared()
        searchTripsRepository.clear()
    }

    fun observeAdapter(searchTripsAdapter: GroupAdapter<ViewHolder>, tripsRequest: SearchTripsRequest) {
        searchTripsRepository.findTrips(tripsRequest, object : SearchTripsDataSource.SearchTripsCallback {
            override fun getTrips(trips: List<Trip>) {
                searchTripsAdapter.apply {
                    clear()
                    addAll(trips.map(::TripItem))
                }
                searchTripsRepository.saveTrips(Pair(tripsRequest, trips))
            }
        })
    }
}