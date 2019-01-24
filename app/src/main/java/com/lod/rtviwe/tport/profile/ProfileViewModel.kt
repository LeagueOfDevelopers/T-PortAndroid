package com.lod.rtviwe.tport.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.utils.CollectionJob

class ProfileViewModel(app: Application) : AndroidViewModel(app) {

    private val compositeJob = CollectionJob()

    override fun onCleared() {
        super.onCleared()
        compositeJob.clear()
    }
}