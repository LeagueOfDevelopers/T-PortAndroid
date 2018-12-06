package com.lod.rtviwe.tport.di

import com.lod.rtviwe.tport.model.recyclerview.SearchItem
import com.lod.rtviwe.tport.viewmodel.BonusesViewModel
import com.lod.rtviwe.tport.viewmodel.OrdersViewModel
import com.lod.rtviwe.tport.viewmodel.ProfileViewModel
import com.lod.rtviwe.tport.viewmodel.RegisterViewModel
import com.lod.rtviwe.tport.viewmodel.SearchViewModel
import com.redmadrobot.inputmask.helper.Mask
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    viewModel<BonusesViewModel>()
    viewModel<OrdersViewModel>()
    viewModel<SearchViewModel>()
    viewModel<ProfileViewModel>()
    viewModel<RegisterViewModel>()
    single { SearchItem("", "", "") }
    single { Mask("+7 ([000]) [000]-[00]-[00]") }
}
