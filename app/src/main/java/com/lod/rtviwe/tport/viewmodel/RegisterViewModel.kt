package com.lod.rtviwe.tport.viewmodel

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(app: Application) : BaseViewModel(app) {

    private val job = Job()

    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun sendCodeToPhoneNumber(phoneNumber: Long) {
        viewModelScope.launch {

        }
    }

    fun checkCode(code: Int) {
        viewModelScope.launch {

        }
    }

    fun registerName() {
        viewModelScope.launch {

        }
    }
}