package com.lod.rtviwe.tport.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.search.wrappers.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SearchViewModel(private val app: Application) : AndroidViewModel(app) {

    private val job = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun populateAdapter(lifecycleOwner: LifecycleOwner, adapter: GroupAdapter<ViewHolder>, searchBox: SearchBox) {
        adapter.apply {
            add(Logo())
            add(SearchBoxItem(searchBox))
            add(Title(app.getString(R.string.popular_title_item)))
            MockTrips.getItems().observe(lifecycleOwner, Observer {
                addAll(it.map(::PopularTrip))
            })
        }
    }
}