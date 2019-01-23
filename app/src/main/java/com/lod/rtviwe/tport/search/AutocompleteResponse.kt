package com.lod.rtviwe.tport.search

data class AutocompleteResponse(
    val suggestions: Array<AutocompleteSuggestion>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AutocompleteResponse

        if (!suggestions.contentEquals(other.suggestions)) return false

        return true
    }

    override fun hashCode(): Int {
        return suggestions.contentHashCode()
    }
}