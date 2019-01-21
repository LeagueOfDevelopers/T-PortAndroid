package com.lod.rtviwe.tport.orders.items

import androidx.recyclerview.widget.LinearLayoutManager
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.current_order_item.view.*

class CurrentOrderCardItem(private val trip: Trip) : Item() {

    override fun getLayout() = R.layout.current_order_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val routesAdapter = GroupAdapter<ViewHolder>()
        val routesLayoutManager = LinearLayoutManager(viewHolder.containerView.context)

        trip.routes.forEachIndexed { index, route ->
            if (index == 0) {
                routesAdapter.add(OrderDestinationFirstItem(route.destination.fromPlace))
            } else {
                routesAdapter.add(OrderDestinationItem(route, isLast = false))
            }

            routesAdapter.add(OrderRouteItem(route))

            if (index == trip.routes.size - 1) {
                routesAdapter.add(OrderDestinationItem(route, isLast = true))
            }
        }

        viewHolder.containerView.recycler_view_order_routes.apply {
            layoutManager = routesLayoutManager
            adapter = routesAdapter
        }
    }
}