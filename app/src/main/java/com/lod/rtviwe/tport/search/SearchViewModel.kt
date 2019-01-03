package com.lod.rtviwe.tport.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.network.autocomplete.AutocompleteApi
import com.lod.rtviwe.tport.search.wrappers.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import timber.log.Timber
import java.util.*

class SearchViewModel(private val app: Application) : AndroidViewModel(app), KoinComponent {

    private val jobAutocomplete = Job()

    private val scopeAutocomplete = CoroutineScope(Dispatchers.Main + jobAutocomplete)

    private val handlerAutocomplete = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting autocomplete: $exception")
    }

    private val autocompleteApi: AutocompleteApi = get()

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
        scopeAutocomplete.launch(handlerAutocomplete) {
            val request = autocompleteApi.getAutocomplete(text, Locale.getDefault().language).await()
            val requestCode = request.code()

            when (requestCode) {
                200 -> {
                    request.body()?.let { array ->
                        callback.autocomplete(array.map { it.name!! })
                        Timber.e(array.map { it.name!! }.toString())
                    }
                }
                else -> Timber.e("Unknown error happened on autocomplete")
            }
        }
    }
}