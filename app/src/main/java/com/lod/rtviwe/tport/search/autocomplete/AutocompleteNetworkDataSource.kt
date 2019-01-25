package com.lod.rtviwe.tport.search.autocomplete

import com.lod.rtviwe.tport.utils.CollectionJob
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

class AutocompleteNetworkDataSource : AutocompleteDataSource, KoinComponent {

    private val collectionJob = CollectionJob()

    init {

        collectionJob.putJobs(JOB_AUTOCOMPLETE)
    }

    private val autocompleteApi: AutocompleteApi by inject()

    override fun autocomplete(text: String, callback: AutocompleteDataSource.AutocompleteCallback) {
        val job = collectionJob.getJob(JOB_AUTOCOMPLETE)

        job?.let {
            it.scope.launch(it.handler) {
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
    }

    override fun saveAutocomplete(value: Pair<String, List<String>>) {
        // TODO save in persistent
    }

    override fun clear() {
        collectionJob.clear()
    }

    companion object {

        private const val AMOUNT_AUTOCOMPLETE_WORDS = 10
        private const val MAX_AUTOCOMPLETE_LENGTH = 32
        private const val JOB_AUTOCOMPLETE = "AUTOCOMPLETE_JOB"
    }
}