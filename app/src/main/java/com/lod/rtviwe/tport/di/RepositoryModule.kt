package com.lod.rtviwe.tport.di

import com.lod.rtviwe.tport.TPortApplication.Companion.MOCK
import com.lod.rtviwe.tport.TPortApplication.Companion.NETWORK
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteRepository
import com.lod.rtviwe.tport.search.searchtrips.SearchTripsRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    single(name = MOCK) { AutocompleteRepository(get(name = MOCK)) }
    single(name = NETWORK) { AutocompleteRepository(get(name = NETWORK)) }
    single(name = MOCK) { SearchTripsRepository(get(name = MOCK)) }
    single(name = NETWORK) { SearchTripsRepository(get(name = NETWORK)) }
}
