package com.lod.rtviwe.tport.search.autocomplete

import com.lod.rtviwe.tport.data.MockAutocomplete

class AutocompleteMockDataSource : AutocompleteDataSource {

    override fun autocomplete(text: String, callback: AutocompleteDataSource.AutocompleteCallback) {
        callback.getAutocomplete(MockAutocomplete.getItems())
    }

    override fun saveAutocomplete(value: Pair<String, List<String>>) {}

    override fun clear() {}
}