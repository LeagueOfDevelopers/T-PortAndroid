package com.lod.rtviwe.tport.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lod.rtviwe.tport.TPortApplication
import com.lod.rtviwe.tport.bonuses.BonusesViewModel
import com.lod.rtviwe.tport.network.autocomplete.AutocompleteApi
import com.lod.rtviwe.tport.network.register.RegistrationApi
import com.lod.rtviwe.tport.network.searchTrips.SearchTripsApi
import com.lod.rtviwe.tport.orders.OrdersViewModel
import com.lod.rtviwe.tport.profile.ProfileViewModel
import com.lod.rtviwe.tport.profile.registration.RegisterViewModel
import com.lod.rtviwe.tport.search.SearchViewModel
import com.lod.rtviwe.tport.search.searchbox.SearchBox
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsViewModel
import com.redmadrobot.inputmask.helper.Mask
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    viewModel<BonusesViewModel>()
    viewModel<OrdersViewModel>()
    viewModel<SearchViewModel>()
    viewModel<ProfileViewModel>()
    viewModel<RegisterViewModel>()
    viewModel<SearchTripsViewModel>()
    single { SearchBox("", "", "") }
    single { Mask("+7 ([000]) [000]-[00]-[00]") }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(TPortApplication.TPORT_URL)
            .build()
            .create(RegistrationApi::class.java)
    }
    single {
        GsonBuilder().setLenient().create()
    }
    single {
        OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val request = chain.request().newBuilder().apply {
                    // TODO hide it
                    addHeader("Authorization", "Token 1cf94e840097454101fdbb0a52ce1ec7ee7ce6ea")
                }.build()
                chain.proceed(request)
            }
        }.build()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(TPortApplication.AUTOCOMPLETE_URL)
            .client(get())
            .build()
            .create(AutocompleteApi::class.java)
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(TPortApplication.TPORT_URL)
            .build()
            .create(SearchTripsApi::class.java)
    }
}
