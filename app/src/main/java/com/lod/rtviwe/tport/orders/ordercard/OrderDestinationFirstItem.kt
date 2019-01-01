package com.lod.rtviwe.tport.orders.ordercard

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Destination
import com.lod.rtviwe.tport.orders.OrderTripClickedListener
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.order_place_item.*

class OrderDestinationFirstItem(private val destination: Destination) : Item() {

    private lateinit var orderTripClickedListener: OrderTripClickedListener

    override fun getLayout() = R.layout.order_place_first_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        when (viewHolder.containerView.context) {
            is OrderTripClickedListener -> orderTripClickedListener =
                    viewHolder.containerView.context as OrderTripClickedListener
            else -> throw ClassCastException("${viewHolder.containerView.context} does not implements SearchListener")
        }

        viewHolder.image_view_connection_bottom.background =
                viewHolder.containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_start)
        viewHolder.text_view_location.text = destination.placeFrom.name
    }
}