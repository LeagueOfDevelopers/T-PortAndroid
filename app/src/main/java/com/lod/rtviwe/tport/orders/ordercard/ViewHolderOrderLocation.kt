package com.lod.rtviwe.tport.orders.ordercard

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.listeners.OrderTripClickedListener
import com.lod.rtviwe.tport.orders.ordercard.wrapper.OrderLocation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.order_location_item.*
import java.text.SimpleDateFormat
import java.util.*

class ViewHolderOrderLocation(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private lateinit var orderTripClickedListener: OrderTripClickedListener

    fun bind(orderLocation: OrderLocation, isFirst: Boolean, isLast: Boolean) {
        when (containerView.context) {
            is OrderTripClickedListener -> orderTripClickedListener = containerView.context as OrderTripClickedListener
            else -> throw ClassCastException("${containerView.context} does not implements SearchListener")
        }

        text_view_arrival_date.text = String.format(
            containerView.context.getString(R.string.arrival_time),
            SimpleDateFormat("hh:mm", Locale.getDefault()).format(orderLocation.destination.arrivalDate)
        )

        when {
            isFirst -> {
                image_view_connection_bottom.background =
                        containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_start)
                text_view_location.text = orderLocation.destination.placeFrom.name
                text_view_arrival_date.text = ""
                // TODO something
                // image_view_location_point
            }
            isLast -> {
                image_view_connection_top.background =
                        containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_end)
                text_view_location.text = orderLocation.destination.placeTo.name
                divider_bottom.visibility = View.GONE
            }
            else -> {
                image_view_connection_top.background =
                        containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_end)
                image_view_connection_bottom.background =
                        containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_start)
                text_view_location.text = orderLocation.destination.placeFrom.name
            }
        }

        card_trip_location_item.setOnClickListener {
            orderTripClickedListener.openTripDetailFragmentFromOrder()
        }
    }
}