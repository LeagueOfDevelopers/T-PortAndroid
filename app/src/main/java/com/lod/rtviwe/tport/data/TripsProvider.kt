package com.lod.rtviwe.tport.data

import com.lod.rtviwe.tport.model.Trip

interface TripsProvider {

    fun getItems(): List<Trip>
}