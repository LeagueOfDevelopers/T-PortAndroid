package com.lod.rtviwe.tport.di

import com.lod.rtviwe.tport.model.recyclerview.SearchItem
import com.lod.rtviwe.tport.viewmodel.BonusesViewModel
import com.lod.rtviwe.tport.viewmodel.OrdersViewModel
import com.lod.rtviwe.tport.viewmodel.ProfileViewModel
import com.lod.rtviwe.tport.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    viewModel<BonusesViewModel>()
    viewModel<OrdersViewModel>()
    viewModel<SearchViewModel>()
    viewModel<ProfileViewModel>()
    single { SearchItem("", "", "") }
}
