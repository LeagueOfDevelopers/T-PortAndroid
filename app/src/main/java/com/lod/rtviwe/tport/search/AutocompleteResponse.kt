package com.lod.rtviwe.tport.search

data class AutocompleteResponse(
    val autocompleteSuggestions: Array<AutocompleteSuggestion>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AutocompleteResponse

        if (!autocompleteSuggestions.contentEquals(other.autocompleteSuggestions)) return false

        return true
    }

    override fun hashCode(): Int {
        return autocompleteSuggestions.contentHashCode()
    }
}