package com.lod.rtviwe.tport.orders

import com.lod.rtviwe.tport.model.FullTrip

interface OrderTripClickedListener {

    fun openTripDetailFragmentFromOrder(fullTrip: FullTrip)
}