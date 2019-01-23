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
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class SearchViewModel(private val app: Application) : AndroidViewModel(app), KoinComponent {

    private val jobAutocomplete = Job()

    private val scopeAutocomplete = CoroutineScope(Dispatchers.Main + jobAutocomplete)

    private val handlerAutocomplete = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting autocomplete: $exception")
    }

    private val autocompleteApi: AutocompleteApi by inject()

    override fun onCleared() {
        super.onCleared()
        jobAutocomplete.cancel()
    }

    fun populateAdapter(lifecycleOwner: LifecycleOwner, adapter: GroupAdapter<ViewHolder>) {
        adapter.clear()
        MockTrips.getItems().observe(lifecycleOwner, Observer { it ->
            adapter.addAll(it.map { Section(PopularTrip(it)) })
        })
    }

    fun findAutocomplete(text: String, callback: (List<String>) -> Unit?) {
        scopeAutocomplete.launch(handlerAutocomplete) {
            val request = autocompleteApi.getAutocomplete(
                AutocompleteRequest(
                    text,
                    AMOUNT_AUTOCOMPLETE_WORDS
                )
            ).await()
            val requestCode = request.code()

            when (requestCode) {
                200 -> {
                    request.body()?.let { array ->
                        Timber.v(array.toString())
                        callback(array.suggestions.map { it.value }.filter { it.length <= MAX_AUTOCOMPLETE_LENGTH })
                    }
                }
                else -> Timber.e("Unknown error happened on dadata.ru")
            }
        }
    }

    companion object {

        private const val AMOUNT_AUTOCOMPLETE_WORDS = 10
        private const val MAX_AUTOCOMPLETE_LENGTH = 32
    }
}