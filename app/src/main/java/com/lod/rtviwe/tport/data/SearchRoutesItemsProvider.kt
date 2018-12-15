package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.Trip

interface SearchRoutesItemsProvider {

    fun getItems(): MutableLiveData<MutableList<Trip>>
}