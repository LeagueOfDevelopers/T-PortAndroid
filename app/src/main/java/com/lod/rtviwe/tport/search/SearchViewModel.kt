package com.lod.rtviwe.tport.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.search.wrappers.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.coroutines.*
import timber.log.Timber

class SearchViewModel(private val app: Application) : AndroidViewModel(app) {

    private val jobAutocomplete = Job()

    private val scopeAutocomplete = CoroutineScope(Dispatchers.Main + jobAutocomplete)

    private val handlerAutocomplete = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting autocomplete: $exception")
    }

    override fun onCleared() {
        super.onCleared()
        jobAutocomplete.cancel()
    }

    fun populateAdapter(lifecycleOwner: LifecycleOwner, adapter: GroupAdapter<ViewHolder>, searchBox: SearchBox) {
        adapter.apply {
            add(Logo())
            add(SearchBoxItem(searchBox))
            add(Title(app.getString(R.string.popular_title_item)))
            MockTrips.getItems().observe(lifecycleOwner, Observer { it ->
                addAll(it.map { Section(PopularTrip(it)) })
            })
        }
    }

    fun findAutocomplete(text: String, callback: AutocompleteCallback) {
        jobAutocomplete.cancelChildren()
        scopeAutocomplete.launch(handlerAutocomplete) {
            callback.autocomplete(listOf("a", "b", "c"))
        }
    }
}