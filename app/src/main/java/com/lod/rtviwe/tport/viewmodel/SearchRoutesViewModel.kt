package com.lod.rtviwe.tport.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.data.MockSearchRoutes
import com.lod.rtviwe.tport.ui.adapter.SearchRoutesAdapter
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

    fun observeAdapter(owner: LifecycleOwner, searchRoutesAdapter: SearchRoutesAdapter) {
        MockSearchRoutes.getItems().observe(owner, Observer {
            searchRoutesAdapter.setData(it)
        })
    }
}