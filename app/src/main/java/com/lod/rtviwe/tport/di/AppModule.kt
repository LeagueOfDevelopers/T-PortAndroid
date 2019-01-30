package com.lod.rtviwe.tport.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lod.rtviwe.tport.BuildConfig
import com.lod.rtviwe.tport.TPortApplication
import com.lod.rtviwe.tport.bonuses.BonusesViewModel
import com.lod.rtviwe.tport.orders.OrdersDataSource
import com.lod.rtviwe.tport.orders.OrdersMockDataSource
import com.lod.rtviwe.tport.orders.OrdersNetworkDataSource
import com.lod.rtviwe.tport.orders.OrdersViewModel
import com.lod.rtviwe.tport.profile.ProfileViewModel
import com.lod.rtviwe.tport.profile.registration.*
import com.lod.rtviwe.tport.search.SearchViewModel
import com.lod.rtviwe.tport.search.autocomplete.*
import com.lod.rtviwe.tport.search.populartrip.PopularTripDataSource
import com.lod.rtviwe.tport.search.populartrip.PopularTripMockDataSource
import com.lod.rtviwe.tport.search.populartrip.PopularTripNetworkDataSource
import com.lod.rtviwe.tport.search.searchtrip.*
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
    viewModel { SearchViewModel(get(), get(name = MOCK), get(name = MOCK)) }
    viewModel<BonusesViewModel>()
    viewModel { OrdersViewModel(get(), get(name = MOCK)) }
    viewModel { RegisterViewModel(get(), get(name = MOCK)) }
    viewModel<ProfileViewModel>()
    viewModel { SearchTripsViewModel(get(), get(name = MOCK)) }
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
        GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
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
    factory<AutocompleteDataSource>(name = NETWORK) { AutocompleteNetworkDataSource() }
    factory<AutocompleteDataSource>(name = MOCK) { AutocompleteMockDataSource() }
    factory<PopularTripDataSource>(name = NETWORK) { PopularTripNetworkDataSource() }
    factory<PopularTripDataSource>(name = MOCK) { PopularTripMockDataSource() }
    factory<OrdersDataSource>(name = NETWORK) { OrdersNetworkDataSource() }
    factory<OrdersDataSource>(name = MOCK) { OrdersMockDataSource() }
    factory<RegistrationDataSource>(name = NETWORK) { RegistrationNetworkDataSource() }
    factory<RegistrationDataSource>(name = MOCK) { RegistrationMockDataSource() }
    factory<SearchTripsDataSource>(name = NETWORK) { SearchTripsNetworkDataSource() }
    factory<SearchTripsDataSource>(name = MOCK) { SearchTripsMockDataSource() }
}

val repositoryModule = module {
    single(name = MOCK) { AutocompleteRepository(get(name = MOCK)) }
    single(name = NETWORK) { AutocompleteRepository(get(name = NETWORK)) }
}

val utilModule = module {
    single { AuthService(get()) }
    single { CountryUtils(get()) }
}

private const val MOCK = "MOCK"
private const val NETWORK = "NETWORK"
