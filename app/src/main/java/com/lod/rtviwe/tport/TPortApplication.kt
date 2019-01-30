package com.lod.rtviwe.tport

import android.app.Application
import com.lod.rtviwe.tport.di.*
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class TPortApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(this, listOf(viewModelModule, networkModule, dataSourceModule, repositoryModule, utilModule))
    }

    companion object {

        const val TPORT_URL = "http://tport-test.lod-misis.ru/"
        const val AUTOCOMPLETE_URL = "https://suggestions.dadata.ru/"
    }
}