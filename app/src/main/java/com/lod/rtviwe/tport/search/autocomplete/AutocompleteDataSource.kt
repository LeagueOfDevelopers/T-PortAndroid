package com.lod.rtviwe.tport.search.autocomplete

interface AutocompleteDataSource {

    interface AutocompleteCallback {

        fun getAutocomplete(words: List<String>)
    }

    fun autocomplete(text: String, callback: AutocompleteCallback)

    fun saveAutocomplete(value: Pair<String, List<String>>)

    fun clear()
}