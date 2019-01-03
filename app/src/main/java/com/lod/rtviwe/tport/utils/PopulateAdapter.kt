package com.lod.rtviwe.tport.utils

import com.lod.rtviwe.tport.model.FullTrip
import com.lod.rtviwe.tport.orders.ordercard.OrderDestinationFirstItem
import com.lod.rtviwe.tport.orders.ordercard.OrderDestinationItem
import com.lod.rtviwe.tport.orders.ordercard.OrderRouteItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

object PopulateAdapter {

    fun fillTripAdapter(adapter: GroupAdapter<ViewHolder>, fullTrip: FullTrip) {
        fullTrip.routes?.forEachIndexed { index, route ->
            if (index == 0) {
                adapter.add(OrderDestinationFirstItem(route.destination))
            } else {
                adapter.add(OrderDestinationItem(route.destination, false))
            }

            adapter.add(OrderRouteItem(route))

            if (index == fullTrip.routes!!.size - 1) {
                adapter.add(OrderDestinationItem(route.destination, true))
            }
        }
    }
}