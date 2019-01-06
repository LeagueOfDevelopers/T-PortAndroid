package com.lod.rtviwe.tport.orders

import com.lod.rtviwe.tport.model.Trip

interface OrderTripClickedListener {

    fun openTripDetailFragmentFromOrder(trip: Trip)
}