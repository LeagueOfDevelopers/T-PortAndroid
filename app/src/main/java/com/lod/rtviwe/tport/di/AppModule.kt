package com.lod.rtviwe.tport.di

import com.lod.rtviwe.tport.model.recyclerview.SearchItem
import com.lod.rtviwe.tport.viewmodel.*
import com.redmadrobot.inputmask.helper.Mask
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    viewModel<BonusesViewModel>()
    viewModel<OrdersViewModel>()
    viewModel<SearchViewModel>()
    viewModel<ProfileViewModel>()
    viewModel<RegisterViewModel>()
    viewModel<SearchRoutesViewModel>()
    single { SearchItem("", "", "") }
    single { Mask("+7 ([000]) [000]-[00]-[00]") }
}
