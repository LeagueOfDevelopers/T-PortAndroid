package com.lod.rtviwe.tport

import android.app.Application
import com.lod.rtviwe.tport.di.mainModule
import org.koin.android.ext.android.startKoin

class TPortApplication : Application() {

    companion object {

        const val URL = "http://tport-test.lod-misis.ru/"
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(mainModule))
    }
}