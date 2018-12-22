package com.lod.rtviwe.tport.search.searchRoute

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.base.BaseViewModel
import com.lod.rtviwe.tport.data.MockTrips
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SearchRoutesViewModel(app: Application) : BaseViewModel(app) {

    private val job = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun observeAdapter(owner: LifecycleOwner, searchRouteCardsAdapter: SearchRouteCardsAdapter) {
        MockTrips.getItems().observe(owner, Observer {
            searchRouteCardsAdapter.setData(it)
        })
    }
}