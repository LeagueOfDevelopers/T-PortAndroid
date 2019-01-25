package com.lod.rtviwe.tport.search.autocomplete

class AutocompleteRepository(
    private val autocompleteDataSource: AutocompleteDataSource
) : AutocompleteDataSource {

    private val cache = hashMapOf<String, List<String>>()

    override fun autocomplete(text: String, callback: AutocompleteDataSource.AutocompleteCallback) {
        val suggestions = cache[text]
        if (suggestions != null) {
            callback.getAutocomplete(suggestions)
        } else {
            autocompleteDataSource.autocomplete(text, callback)
        }
    }

    override fun saveAutocomplete(value: Pair<String, List<String>>) {
        cache[value.first] = value.second
        autocompleteDataSource.saveAutocomplete(value)
    }

    override fun clear() {
        autocompleteDataSource.clear()
    }
}