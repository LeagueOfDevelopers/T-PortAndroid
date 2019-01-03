package com.lod.rtviwe.tport.network.autocomplete

data class AutocompleteSuggestions(
    val code: String?,
    val mainAirportName: String?,
    val countryCases: String?,
    val indexStrings: Array<String>?,
    val weight: Int?,
    val cases: HashMap<String, String>?,
    val countryName: String?,
    val type: String?,
    val countryCode: String?,
    val coordinates: HashMap<String, String>?,
    val name: String?,
    val state_code: String?
)