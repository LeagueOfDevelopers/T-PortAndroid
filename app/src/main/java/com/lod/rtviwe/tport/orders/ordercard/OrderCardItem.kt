package com.lod.rtviwe.tport.orders.ordercard

import androidx.recyclerview.widget.LinearLayoutManager
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.FullTrip
import com.lod.rtviwe.tport.orders.OrderTripClickedListener
import com.lod.rtviwe.tport.utils.PopulateAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.order_item.view.*

class OrderCardItem(private val fullTrip: FullTrip) : Item() {

    private lateinit var orderTripClickedListener: OrderTripClickedListener

    override fun getLayout() = R.layout.order_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        when (viewHolder.containerView.context) {
            is OrderTripClickedListener -> orderTripClickedListener =
                    viewHolder.containerView.context as OrderTripClickedListener
            else -> throw ClassCastException("${viewHolder.containerView.context} does not implements SearchListener")
        }

        val routesAdapter = GroupAdapter<ViewHolder>()
        val routesLayoutManager = LinearLayoutManager(viewHolder.containerView.context)

        PopulateAdapter.fillTripAdapter(routesAdapter, fullTrip)

        viewHolder.containerView.recycler_view_order_routes.apply {
            layoutManager = routesLayoutManager
            adapter = routesAdapter
        }
    }
}