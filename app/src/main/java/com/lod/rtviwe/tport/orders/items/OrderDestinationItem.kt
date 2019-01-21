package com.lod.rtviwe.tport.orders.items

import android.view.View
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Route
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.order_place_item.*
import java.text.DateFormat

class OrderDestinationItem(private val route: Route, private val isLast: Boolean) : Item() {

    override fun getLayout() = R.layout.order_place_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val date = if (isLast) route.arrivalDate else route.departureDate
        viewHolder.text_view_arrival_date.text = String.format(
            viewHolder.containerView.context.getString(R.string.arrival_time),
            DateFormat.getTimeInstance().format(date)
        )

        viewHolder.image_view_connection_top.background =
                viewHolder.containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_end)

        if (isLast) {
            viewHolder.text_view_location.text = route.destination.toPlace.name
            viewHolder.divider_bottom.visibility = View.GONE
        } else {
            viewHolder.image_view_connection_bottom.background =
                    viewHolder.containerView.context.getDrawable(R.drawable.connection_rectangle_vertical_start)
            viewHolder.text_view_location.text = route.destination.fromPlace.name
        }
    }
}