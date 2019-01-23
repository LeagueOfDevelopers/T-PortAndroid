package com.lod.rtviwe.tport.search

interface AutocompleteDataSource {

    interface AutocompleteCallback {

        fun autocomplete(words: List<String>)
    }

    fun getAutocomplete(text: String, callback: AutocompleteDataSource.AutocompleteCallback)

    fun clear()
}