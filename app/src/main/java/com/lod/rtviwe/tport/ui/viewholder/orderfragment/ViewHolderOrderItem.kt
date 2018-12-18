package com.lod.rtviwe.tport.ui.viewholder.orderfragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.model.ordersfragment.OrderItem
import com.lod.rtviwe.tport.model.ordersfragment.OrderLocation
import com.lod.rtviwe.tport.model.ordersfragment.OrderRoute
import com.lod.rtviwe.tport.ui.adapter.order.OrderRoutesAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.order_item.*

class ViewHolderOrderItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(tripItem: Trip) {
        val orderRoutesItemAdapter = OrderRoutesAdapter(containerView.context, listOf())
        val orderRoutesLayoutManager = LinearLayoutManager(containerView.context, RecyclerView.VERTICAL, false)

        val routesAndLocations = mutableListOf<OrderItem>()
        tripItem.routes.forEachIndexed { index, route ->
            routesAndLocations.add(OrderLocation(route.destination))
            routesAndLocations.add(OrderRoute(route))
            if (index == tripItem.routes.size - 1) {
                routesAndLocations.add(OrderLocation(route.destination))
            }
        }

        orderRoutesItemAdapter.setData(routesAndLocations)

        recycler_view_order_routes_and_locations.apply {
            adapter = orderRoutesItemAdapter
            layoutManager = orderRoutesLayoutManager
        }
    }
}