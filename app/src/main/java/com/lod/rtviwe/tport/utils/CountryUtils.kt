package com.lod.rtviwe.tport.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lod.rtviwe.tport.R

class CountryUtils(private val context: Context) {

    fun findCountry(phoneNumber: String): String? {
        val typeToken = object : TypeToken<CountriesJson>() {}.type

        context.assets.open(COUNTRIES_FILE).bufferedReader().use {
            val countriesJson = Gson().fromJson<CountriesJson>(it, typeToken)
            return countriesJson.countries.find { country -> phoneNumber.startsWith(country.code) }?.name
        }
    }

    fun findFragResource(country: String) = when (country) {
        "Russia" -> R.drawable.ic_russia_flag
        // TODO
        else -> R.drawable.ic_empty_flag
    }

    private data class CountriesJson(
        val countries: Array<Country>
    )

    private data class Country(
        val code: String,
        val name: String
    )

    companion object {

        private const val COUNTRIES_FILE = "countries.json"
    }
}