package com.lod.rtviwe.tport.search.autocomplete

import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class AutocompleteNetworkDataSource : AutocompleteDataSource, KoinComponent {

    private val jobAutocomplete = Job()

    private val scopeAutocomplete = CoroutineScope(Dispatchers.Main + jobAutocomplete)

    private val handlerAutocomplete = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting getAutocomplete: $exception")
    }

    private val autocompleteApi: AutocompleteApi by inject()

    override fun autocomplete(text: String, callback: AutocompleteDataSource.AutocompleteCallback) {
        scopeAutocomplete.launch(handlerAutocomplete) {
            val request = autocompleteApi.getAutocompleteAsync(
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
                        callback.getAutocomplete(array.suggestions.map { it.value }.filter { it.length <= MAX_AUTOCOMPLETE_LENGTH })
                    }
                }
                else -> Timber.e("Unknown error happened on dadata.ru")
            }
        }
    }

    override fun clear() {
        jobAutocomplete.cancel()
    }

    companion object {

        private const val AMOUNT_AUTOCOMPLETE_WORDS = 10
        private const val MAX_AUTOCOMPLETE_LENGTH = 32
    }
}