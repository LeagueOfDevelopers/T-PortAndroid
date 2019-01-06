package com.lod.rtviwe.tport.search.searchtrip

import com.lod.rtviwe.tport.model.Trip

interface SearchTripClickedListener {

    fun openTripDetailsFragmentFromSearch(trip: Trip)
}