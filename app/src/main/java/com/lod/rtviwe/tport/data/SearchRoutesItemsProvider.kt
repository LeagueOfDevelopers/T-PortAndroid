package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.FullTrip


interface SearchRoutesItemsProvider {

    fun getItems(): MutableLiveData<MutableList<FullTrip>>
}