package com.lod.rtviwe.tport.search

import android.app.Application
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteDataSource
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteNetworkDataSource
import com.lod.rtviwe.tport.search.populartrip.PopularTrip
import com.lod.rtviwe.tport.search.populartrip.PopularTripDataSource
import com.lod.rtviwe.tport.search.populartrip.PopularTripNetworkDataSource
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SearchViewModel(private val app: Application) : AndroidViewModel(app), KoinComponent {

    private val autocompleteRepository: AutocompleteNetworkDataSource by inject()
    private val popularTripRepository: PopularTripNetworkDataSource by inject()

    override fun onCleared() {
        super.onCleared()
        autocompleteRepository.clear()
        popularTripRepository.clear()
    }

    fun populateAdapter(lifecycleOwner: LifecycleOwner, adapter: GroupAdapter<ViewHolder>) {
        popularTripRepository.setPopularTrips(lifecycleOwner, object : PopularTripDataSource.PopularTripCallback {
            override fun getPopularTrips(trips: List<Trip>) {
                adapter.clear()
                trips.forEach {
                    adapter.add(PopularTrip(it))
                }
            }
        })
    }

    fun findAutocomplete(text: String, textView: AutoCompleteTextView) {
        autocompleteRepository.autocomplete(text, object : AutocompleteDataSource.AutocompleteCallback {
            override fun getAutocomplete(words: List<String>) {
                textView.setAdapter(ArrayAdapter(app, android.R.layout.simple_dropdown_item_1line, words))
            }
        })
    }
}