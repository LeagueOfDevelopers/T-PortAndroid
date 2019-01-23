package com.lod.rtviwe.tport.search

data class AutocompleteSuggestion(
    val data: AutoCompleteData,
    val unrestricted_value: String,
    val value: String
)