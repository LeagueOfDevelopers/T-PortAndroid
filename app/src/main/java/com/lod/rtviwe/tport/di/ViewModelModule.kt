package com.lod.rtviwe.tport.di

import com.lod.rtviwe.tport.TPortApplication.Companion.MOCK
import com.lod.rtviwe.tport.TPortApplication.Companion.NETWORK
import com.lod.rtviwe.tport.bonuses.BonusesViewModel
import com.lod.rtviwe.tport.orders.OrdersViewModel
import com.lod.rtviwe.tport.profile.ProfileViewModel
import com.lod.rtviwe.tport.profile.registration.RegisterViewModel
import com.lod.rtviwe.tport.search.SearchViewModel
import com.lod.rtviwe.tport.search.searchtrips.SearchTripsViewModel
import com.lod.rtviwe.tport.tripdetails.TripDetailsViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { SearchViewModel(get(), get(name = NETWORK), get(name = MOCK)) }
    viewModel<BonusesViewModel>()
    viewModel { OrdersViewModel(get(), get(name = MOCK)) }
    viewModel { RegisterViewModel(get(), get(name = NETWORK)) }
    viewModel<ProfileViewModel>()
    viewModel { SearchTripsViewModel(get(), get(name = NETWORK)) }
    viewModel<TripDetailsViewModel>()
}
