package com.lod.rtviwe.tport.utils

import android.content.Context

class AuthService(private val context: Context) {

    fun putToken(token: String) {
        context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE).edit().apply {
            putString(TOKEN, token)
            apply()
        }
    }

    fun getToken(): String? = context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE).getString(TOKEN, null)

    fun putName(name: String) {
        context.getSharedPreferences(NAME_PREFS, Context.MODE_PRIVATE).edit().apply {
            putString(NAME, name)
            apply()
        }
    }

    fun getName(): String? = context.getSharedPreferences(NAME_PREFS, Context.MODE_PRIVATE).getString(NAME, null)

    fun checkPhoneNumber(phoneNumber: String) = phoneNumber.length == PHONE_NUMBER_LENGTH

    fun checkCodeLength(code: String) = (code.length == CODE_LENGTH)

    companion object {

        const val CODE_LENGTH = 4
        const val PHONE_NUMBER_LENGTH = 18

        private const val TOKEN_PREFS = "TPORT_TOKEN_PREFERENCES"
        private const val TOKEN = "TPORT_TOKEN"
        private const val NAME_PREFS = "NAME_PREFERENCES"
        private const val NAME = "NAME"
    }
}