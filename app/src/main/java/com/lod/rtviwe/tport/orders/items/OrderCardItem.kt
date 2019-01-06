package com.lod.rtviwe.tport.orders.items

import androidx.recyclerview.widget.LinearLayoutManager
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.orders.OrderTripClickedListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.order_item.view.*

class OrderCardItem(private val trip: Trip) : Item() {

    private lateinit var orderTripClickedListener: OrderTripClickedListener

    override fun getLayout() = R.layout.order_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        when (viewHolder.containerView.context) {
            is OrderTripClickedListener -> orderTripClickedListener =
                    viewHolder.containerView.context as OrderTripClickedListener
            else -> throw ClassCastException("${viewHolder.containerView.context} does not implement SearchListener")
        }

        val routesAdapter = GroupAdapter<ViewHolder>()
        val routesLayoutManager = LinearLayoutManager(viewHolder.containerView.context)

        trip.routes.forEachIndexed { index, route ->
            if (index == 0) {
                routesAdapter.add(OrderDestinationFirstItem(route.destination.fromPlace))
            } else {
                routesAdapter.add(OrderDestinationItem(route, false))
            }

            routesAdapter.add(OrderRouteItem(route))

            if (index == trip.routes.size - 1) {
                routesAdapter.add(OrderDestinationItem(route, true))
            }
        }

        viewHolder.containerView.recycler_view_order_routes.apply {
            layoutManager = routesLayoutManager
            adapter = routesAdapter
        }
    }
}