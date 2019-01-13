package com.lod.rtviwe.tport.utils

import android.content.Context

class AuthService(private val context: Context) {

    fun putToken(token: String) {
        context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE).edit().apply {
            putString(TOKEN, token)
            apply()
        }
    }

    fun getToken(context: Context) =
        context.getSharedPreferences(
            TOKEN_PREFS,
            Context.MODE_PRIVATE
        ).getString(TOKEN, null)

    companion object {

        const val TOKEN_PREFS = "user token"
        const val TOKEN = "token"
    }
}