package com.lod.rtviwe.tport.orders.ordercard

import android.view.View
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.listeners.OrderTripClickedListener
import com.lod.rtviwe.tport.model.Destination
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.order_place_item.*
import java.text.SimpleDateFormat
import java.util.*

class OrderDestination(
    private val destination: Destination,
    private val isFirst: Boolean,
    private val isLast: Boolean
) : Item() {

    private lateinit var orderTripClickedListener: OrderTripClickedListener

    override fun getLayout() = R.layout.order_place_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        when (viewHolder.containerView.context) {
            is OrderTripClickedListener -> orderTripClickedListener =
                    viewHolder.containerView.context as OrderTripClickedListener
            else -> throw ClassCastException("${viewHolder.containerView.context} does not implements SearchListener")
        }

        viewHolder.text_view_arrival_date.text = String.format(
            viewHolder.containerView.context.getString(R.string.arrival_time),
            SimpleDateFormat("hh:mm", Locale.getDefault()).format(destination.arrivalDate)
        )

        when {
            isFirst -> {
                viewHolder.image_view_connection_bottom.background =
                        viewHolder.containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_start)
                viewHolder.text_view_location.text = destination.placeFrom.name
                viewHolder.text_view_arrival_date.text = ""
                // TODO something
                // image_view_location_point
            }
            isLast -> {
                viewHolder.image_view_connection_top.background =
                        viewHolder.containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_end)
                viewHolder.text_view_location.text = destination.placeTo.name
                viewHolder.divider_bottom.visibility = View.GONE
            }
            else -> {
                viewHolder.image_view_connection_top.background =
                        viewHolder.containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_end)
                viewHolder.image_view_connection_bottom.background =
                        viewHolder.containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_start)
                viewHolder.text_view_location.text = destination.placeFrom.name
            }
        }

        viewHolder.card_trip_location_item.setOnClickListener {
            orderTripClickedListener.openTripDetailFragmentFromOrder()
        }
    }
}