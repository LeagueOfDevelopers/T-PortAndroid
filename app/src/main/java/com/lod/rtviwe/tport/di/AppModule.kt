package com.lod.rtviwe.tport.di

import com.lod.rtviwe.tport.model.recyclerview.SearchItem
import com.lod.rtviwe.tport.viewmodel.*
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    viewModel<BonusesViewModel>()
    viewModel<OrdersViewModel>()
    viewModel<SearchViewModel>()
    viewModel<ProfileViewModel>()
    viewModel<RegisterViewModel>()
    single { SearchItem("", "", "") }
}
