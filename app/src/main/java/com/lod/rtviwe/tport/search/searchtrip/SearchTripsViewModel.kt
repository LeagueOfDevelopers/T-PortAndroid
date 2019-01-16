package com.lod.rtviwe.tport.search.searchtrip

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.network.searchTrips.SearchTripsApi
import com.lod.rtviwe.tport.network.searchTrips.TripsRequest
import com.lod.rtviwe.tport.search.searchtrip.items.TripItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import timber.log.Timber

class SearchTripsViewModel(app: Application) : AndroidViewModel(app), KoinComponent {

    private val jobGetTrips = Job()

    private val scopeGetTrips = CoroutineScope(Dispatchers.Main + jobGetTrips)

    private val handlerGetTrips = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting trips: $exception")
    }

    private val searchTripsApi: SearchTripsApi = get()

    override fun onCleared() {
        super.onCleared()
        jobGetTrips.cancel()
    }

    fun observeAdapter(
        owner: LifecycleOwner,
        searchTripsAdapter: GroupAdapter<ViewHolder>,
        tripsRequest: TripsRequest
    ) {
        scopeGetTrips.launch(handlerGetTrips) {
            // TODO wait for David
//            val request = searchTripsApi.search(tripsRequest).await()
//            val code = request.code()
//
//            when (code) {
//                200 -> MockTrips.getItems().observe(owner, Observer {
//                    searchTripsAdapter.addAll(it.map(::TripItem))
//                })
//                else -> Timber.e("Unknown error happened on David $code")
//            }

            MockTrips.getItems().observe(owner, Observer {
                searchTripsAdapter.addAll(it.map(::TripItem))
            })
        }
    }
}