package com.lod.rtviwe.tport

import android.app.Application
import android.content.Context
import com.lod.rtviwe.tport.di.mainModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class TPortApplication : Application() {

    companion object {

        private const val TOKEN_PREFS = "user token"
        private const val TOKEN = "token"

        const val TPORT_URL = "http://tport-AutocompleteSuggestions.lod-misis.ru/"
        const val AUTOCOMPLETE_URL = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/"

        fun putToken(context: Context, token: String) {
            context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE).edit().apply {
                putString(TOKEN, token)
                apply()
            }
        }

        fun getToken(context: Context) =
            context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE).getString(TOKEN, "")
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(this, listOf(mainModule))
    }
}