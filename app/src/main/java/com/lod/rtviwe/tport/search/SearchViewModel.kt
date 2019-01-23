package com.lod.rtviwe.tport.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.search.items.PopularTrip
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SearchViewModel(app: Application) : AndroidViewModel(app), KoinComponent {

    private val autocompleteRepository: AutocompleteRepository by inject()

    override fun onCleared() {
        super.onCleared()
        autocompleteRepository.clear()
    }

    fun populateAdapter(lifecycleOwner: LifecycleOwner, adapter: GroupAdapter<ViewHolder>) {
        adapter.clear()
        MockTrips.getItems().observe(lifecycleOwner, Observer { it ->
            adapter.addAll(it.map { Section(PopularTrip(it)) })
        })
    }

    fun findAutocomplete(text: String, callback: (List<String>) -> Unit) {
        autocompleteRepository.getAutocomplete(text, object : AutocompleteDataSource.AutocompleteCallback {
            override fun autocomplete(words: List<String>) {
                callback(words)
            }
        })
    }
}