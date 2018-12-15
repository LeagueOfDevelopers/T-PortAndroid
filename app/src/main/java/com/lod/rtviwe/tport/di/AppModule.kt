package com.lod.rtviwe.tport.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lod.rtviwe.tport.TPortApplication
import com.lod.rtviwe.tport.model.searchfragment.SearchItem
import com.lod.rtviwe.tport.network.RegistrationApi
import com.lod.rtviwe.tport.viewmodel.*
import com.redmadrobot.inputmask.helper.Mask
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
    viewModel<SearchRoutesViewModel>()
    single { SearchItem("", "", "") }
    single { Mask("+7 ([000]) [000]-[00]-[00]") }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(TPortApplication.URL)
            .build()
            .create(RegistrationApi::class.java)
    }
}
