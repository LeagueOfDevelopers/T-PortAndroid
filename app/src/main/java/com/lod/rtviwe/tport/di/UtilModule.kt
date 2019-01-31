package com.lod.rtviwe.tport.di

import com.lod.rtviwe.tport.utils.AuthService
import com.lod.rtviwe.tport.utils.CountryUtils
import org.koin.dsl.module.module

val utilModule = module {
    single { AuthService(get()) }
    single { CountryUtils(get()) }
}