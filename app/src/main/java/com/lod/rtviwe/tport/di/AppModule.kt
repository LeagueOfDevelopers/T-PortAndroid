package com.lod.rtviwe.tport.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lod.rtviwe.tport.BuildConfig
import com.lod.rtviwe.tport.TPortApplication
import com.lod.rtviwe.tport.bonuses.BonusesViewModel
import com.lod.rtviwe.tport.orders.OrdersNetworkDataSource
import com.lod.rtviwe.tport.orders.OrdersViewModel
import com.lod.rtviwe.tport.profile.ProfileViewModel
import com.lod.rtviwe.tport.profile.registration.RegisterViewModel
import com.lod.rtviwe.tport.profile.registration.RegistrationApi
import com.lod.rtviwe.tport.profile.registration.RegistrationNetworkDataSource
import com.lod.rtviwe.tport.search.SearchViewModel
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteApi
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteNetworkDataSource
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteRepository
import com.lod.rtviwe.tport.search.populartrip.PopularTripNetworkDataSource
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsApi
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsNetworkDataSource
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsViewModel
import com.lod.rtviwe.tport.tripdetails.TripDetailsViewModel
import com.lod.rtviwe.tport.utils.AuthService
import com.lod.rtviwe.tport.utils.CountryUtils
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        SearchViewModel(get(), AutocompleteRepository(AutocompleteNetworkDataSource()), PopularTripNetworkDataSource())
    }
    viewModel<BonusesViewModel>()
    viewModel { OrdersViewModel(get(), OrdersNetworkDataSource()) }
    viewModel { RegisterViewModel(get(), RegistrationNetworkDataSource()) }
    viewModel<ProfileViewModel>()
    viewModel { SearchTripsViewModel(get(), SearchTripsNetworkDataSource()) }
    viewModel<TripDetailsViewModel>()
}

val networkModule = module {
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
                    addHeader("Authorization", BuildConfig.DADATA_API)
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

val utilModule = module {
    single { AuthService(get()) }
    single { CountryUtils(get()) }
}
