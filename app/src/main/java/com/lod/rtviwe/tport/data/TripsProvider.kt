package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData


interface TripsProvider {

    fun getItems(): MutableLiveData<MutableList<FullTrip>>
}