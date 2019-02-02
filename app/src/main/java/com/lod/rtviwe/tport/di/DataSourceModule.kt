package com.lod.rtviwe.tport.di

import com.lod.rtviwe.tport.TPortApplication.Companion.MOCK
import com.lod.rtviwe.tport.TPortApplication.Companion.NETWORK
import com.lod.rtviwe.tport.orders.OrdersDataSource
import com.lod.rtviwe.tport.orders.OrdersMockDataSource
import com.lod.rtviwe.tport.orders.OrdersNetworkDataSource
import com.lod.rtviwe.tport.profile.registration.RegistrationDataSource
import com.lod.rtviwe.tport.profile.registration.RegistrationMockDataSource
import com.lod.rtviwe.tport.profile.registration.RegistrationNetworkDataSource
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteDataSource
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteMockDataSource
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteNetworkDataSource
import com.lod.rtviwe.tport.search.populartrip.PopularTripDataSource
import com.lod.rtviwe.tport.search.populartrip.PopularTripMockDataSource
import com.lod.rtviwe.tport.search.populartrip.PopularTripNetworkDataSource
import com.lod.rtviwe.tport.search.searchtrips.SearchTripsDataSource
import com.lod.rtviwe.tport.search.searchtrips.SearchTripsMockDataSource
import com.lod.rtviwe.tport.search.searchtrips.SearchTripsNetworkDataSource
import org.koin.dsl.module.module

val dataSourceModule = module {
    factory<AutocompleteDataSource>(name = NETWORK) { AutocompleteNetworkDataSource() }
    factory<AutocompleteDataSource>(name = MOCK) { AutocompleteMockDataSource() }
    factory<PopularTripDataSource>(name = NETWORK) { PopularTripNetworkDataSource() }
    factory<PopularTripDataSource>(name = MOCK) { PopularTripMockDataSource() }
    factory<OrdersDataSource>(name = NETWORK) { OrdersNetworkDataSource() }
    factory<OrdersDataSource>(name = MOCK) { OrdersMockDataSource() }
    factory<RegistrationDataSource>(name = NETWORK) { RegistrationNetworkDataSource() }
    factory<RegistrationDataSource>(name = MOCK) { RegistrationMockDataSource() }
    single<SearchTripsDataSource>(name = NETWORK) { SearchTripsNetworkDataSource() }
    factory<SearchTripsDataSource>(name = MOCK) { SearchTripsMockDataSource() }
}