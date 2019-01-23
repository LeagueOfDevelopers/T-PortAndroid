package com.lod.rtviwe.tport

import android.app.Application
import com.lod.rtviwe.tport.di.networkModule
import com.lod.rtviwe.tport.di.uiModule
import com.lod.rtviwe.tport.di.utilModule
import com.lod.rtviwe.tport.di.viewModelModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class TPortApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(this, listOf(viewModelModule, uiModule, networkModule, utilModule))
    }

    companion object {

        const val TPORT_URL = "http://tport-test.lod-misis.ru/"
        const val AUTOCOMPLETE_URL = "https://autocompleteSuggestions.dadata.ru/"
    }
}