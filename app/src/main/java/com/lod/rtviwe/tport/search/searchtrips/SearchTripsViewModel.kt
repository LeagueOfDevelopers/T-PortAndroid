package com.lod.rtviwe.tport.search.searchtrips

import android.app.Application
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.searchtrips.items.TripItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class SearchTripsViewModel(
    app: Application,
    private val searchTripsRepository: SearchTripsRepository
) : AndroidViewModel(app) {

    override fun onCleared() {
        super.onCleared()
        searchTripsRepository.clear()
    }

    // does not work twice
    fun observeAdapter(
        searchTripsAdapter: GroupAdapter<ViewHolder>,
        tripsRequest: SearchTripsRequest,
        progressBar: ProgressBar
    ) {
        progressBar.visibility = View.VISIBLE
        searchTripsRepository.findTrips(tripsRequest, object : SearchTripsDataSource.SearchTripsCallback {
            override fun getTrips(trips: List<Trip>) {
                searchTripsAdapter.apply {
                    clear()
                    addAll(trips.map(::TripItem))
                }
                progressBar.visibility = View.INVISIBLE
                searchTripsRepository.saveTrips(Pair(tripsRequest, trips))
            }
        })
    }
}