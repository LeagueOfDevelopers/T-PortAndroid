package com.lod.rtviwe.tport.viewmodel

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class BonusesViewModel(app: Application) : BaseViewModel(app) {

    private val job = Job()

    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}