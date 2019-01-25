package com.lod.rtviwe.tport.tripdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.orders.items.OrderDestinationFirstItem
import com.lod.rtviwe.tport.orders.items.OrderDestinationItem
import com.lod.rtviwe.tport.orders.items.OrderRouteItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class TripDetailsViewModel(app: Application) : AndroidViewModel(app) {

    fun populateAdapter(adapter: GroupAdapter<ViewHolder>, trip: Trip) {
        trip.routes.forEachIndexed { index, route ->
            if (index == 0) {
                adapter.add(OrderDestinationFirstItem(route.destination.fromPlace))
            } else {
                adapter.add(OrderDestinationItem(route, false))
            }

            adapter.add(OrderRouteItem(route))

            if (index == trip.routes.size - 1) {
                adapter.add(OrderDestinationItem(route, true))
            }
        }
    }
}