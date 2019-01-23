package com.lod.rtviwe.tport.utils

import android.content.Context

class AuthService(private val context: Context) {

    fun putToken(token: String) {
        context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE).edit().apply {
            putString(TOKEN, token)
            apply()
        }
    }

    fun getToken(context: Context): String? =
        context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE).getString(TOKEN, null)

    companion object {

        private const val TOKEN_PREFS = "TPORT_TOKEN_PREFERENCES"
        private const val TOKEN = "TPORT_TOKEN"
    }
}