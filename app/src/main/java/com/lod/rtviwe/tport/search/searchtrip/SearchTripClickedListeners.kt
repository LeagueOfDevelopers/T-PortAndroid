package com.lod.rtviwe.tport.search.searchtrip

import com.lod.rtviwe.tport.model.FullTrip

interface SearchTripClickedListener {

    fun openTripDetailFragmentFromSearch(fullTrip: FullTrip)
}

interface OrderTripClickedListener {

    fun openTripDetailFragmentFromOrder(fullTrip: FullTrip)
}