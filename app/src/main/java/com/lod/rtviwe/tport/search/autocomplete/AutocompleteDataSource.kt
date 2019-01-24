package com.lod.rtviwe.tport.search.autocomplete

interface AutocompleteDataSource {

    interface AutocompleteCallback {

        fun getAutocomplete(words: List<String>)
    }

    fun autocomplete(text: String, callback: AutocompleteCallback)

    fun clear()
}