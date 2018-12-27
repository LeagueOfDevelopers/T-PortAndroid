package com.lod.rtviwe.tport

import android.app.Application
import com.lod.rtviwe.tport.di.mainModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class TPortApplication : Application() {

    companion object {

        const val URL = "http://tport-test.lod-misis.ru/"
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(this, listOf(mainModule))
    }
}