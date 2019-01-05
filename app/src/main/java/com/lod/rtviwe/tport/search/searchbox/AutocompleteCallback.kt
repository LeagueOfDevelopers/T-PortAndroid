package com.lod.rtviwe.tport.search.searchbox

interface AutocompleteCallback {

    fun autocomplete(destinationWords: List<DestinationWord>)
}