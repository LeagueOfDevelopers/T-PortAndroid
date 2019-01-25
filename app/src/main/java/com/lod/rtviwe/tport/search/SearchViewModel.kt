package com.lod.rtviwe.tport.search

import android.app.Application
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteDataSource
import com.lod.rtviwe.tport.search.populartrip.PopularTrip
import com.lod.rtviwe.tport.search.populartrip.PopularTripDataSource
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import org.koin.standalone.KoinComponent

class SearchViewModel(
    private val app: Application,
    private val autocompleteRepository: AutocompleteDataSource,
    private val popularTripRepository: PopularTripDataSource
) : AndroidViewModel(app), KoinComponent {

    override fun onCleared() {
        super.onCleared()
        autocompleteRepository.clear()
        popularTripRepository.clear()
    }

    fun populateAdapter(adapter: GroupAdapter<ViewHolder>) {
        popularTripRepository.setPopularTrips(object : PopularTripDataSource.PopularTripCallback {
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

    fun navigateToSearchTrip(navController: NavController, searchBox: SearchBox) {
        val bundle = Bundle().apply {
            putString(SearchTripsFragment.ARGUMENT_FROM_PLACE, searchBox.fromPlace)
            putString(SearchTripsFragment.ARGUMENT_TO_PLACE, searchBox.toPlace)
            putString(SearchTripsFragment.ARGUMENT_TRAVEL_TIME, searchBox.travelTime)
        }
        navController.navigate(R.id.action_searchFragment_to_searchTripsFragment, bundle)
    }
}