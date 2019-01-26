package com.lod.rtviwe.tport.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lod.rtviwe.tport.BuildConfig
import com.lod.rtviwe.tport.TPortApplication
import com.lod.rtviwe.tport.bonuses.BonusesViewModel
import com.lod.rtviwe.tport.orders.OrdersDataSource
import com.lod.rtviwe.tport.orders.OrdersNetworkDataSource
import com.lod.rtviwe.tport.orders.OrdersViewModel
import com.lod.rtviwe.tport.profile.ProfileViewModel
import com.lod.rtviwe.tport.profile.registration.RegisterViewModel
import com.lod.rtviwe.tport.profile.registration.RegistrationApi
import com.lod.rtviwe.tport.profile.registration.RegistrationDataSource
import com.lod.rtviwe.tport.profile.registration.RegistrationNetworkDataSource
import com.lod.rtviwe.tport.search.SearchViewModel
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteApi
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteDataSource
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteNetworkDataSource
import com.lod.rtviwe.tport.search.autocomplete.AutocompleteRepository
import com.lod.rtviwe.tport.search.populartrip.PopularTripDataSource
import com.lod.rtviwe.tport.search.populartrip.PopularTripNetworkDataSource
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsApi
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsDataSource
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
    viewModel { SearchViewModel(get(), get(), get()) }
    viewModel<BonusesViewModel>()
    viewModel { OrdersViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel<ProfileViewModel>()
    viewModel { SearchTripsViewModel(get(), get()) }
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
        GsonBuilder().setLenient()/*.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")*/.create()
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
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(TPortApplication.TPORT_URL)
            .build()
            .create(SearchTripsApi::class.java)
    }
}

val dataSourceModule = module {
    factory<AutocompleteDataSource> { AutocompleteNetworkDataSource() }
    factory { AutocompleteRepository(get()) }
    factory<PopularTripDataSource> { PopularTripNetworkDataSource() }
    factory<OrdersDataSource> { OrdersNetworkDataSource() }
    factory<RegistrationDataSource> { RegistrationNetworkDataSource() }
    factory<SearchTripsDataSource> { SearchTripsNetworkDataSource() }
}

val utilModule = module {
    single { AuthService(get()) }
    single { CountryUtils(get()) }
}
