package com.lod.rtviwe.tport.listeners

import com.lod.rtviwe.tport.model.FullTrip

interface SearchTripClickedListener {

    fun openTripDetailFragmentFromSearch(fullTrip: FullTrip)
}

interface OrderTripClickedListener {

    fun openTripDetailFragmentFromOrder(fullTrip: FullTrip)
}