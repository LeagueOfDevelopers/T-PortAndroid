package com.lod.rtviwe.tport.search.searchroute

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.search.searchroute.searchroutecard.TripItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SearchRoutesViewModel(app: Application) : AndroidViewModel(app) {

    private val job = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun observeAdapter(owner: LifecycleOwner, searchRouteCardsAdapter: GroupAdapter<ViewHolder>) {
        MockTrips.getItems().observe(owner, Observer {
            searchRouteCardsAdapter.addAll(it.map(::TripItem))
        })
    }
}