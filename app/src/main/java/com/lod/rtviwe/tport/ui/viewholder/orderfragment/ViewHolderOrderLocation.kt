package com.lod.rtviwe.tport.ui.viewholder.orderfragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.ordersfragment.OrderLocation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.order_location_item.*
import java.text.SimpleDateFormat
import java.util.*

class ViewHolderOrderLocation(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(orderLocation: OrderLocation, isFirst: Boolean, isLast: Boolean) {
        text_view_location.text = if (isLast)
            orderLocation.destination.placeTo
        else
            orderLocation.destination.placeFrom

        text_view_arrival_date.text = String.format(
            containerView.context.getString(R.string.arrival_time),
            SimpleDateFormat("hh:mm", Locale.getDefault()).format(orderLocation.destination.arrivalDate)
        )

        when {
            isFirst -> {
                image_view_connection_bottom.setImageResource(R.drawable.ic_arrow_downward_black_24dp)
            }
            isLast -> {
                image_view_connection_top.setImageResource(R.drawable.ic_arrow_downward_black_24dp)
            }
            else -> {
                image_view_connection_top.setImageResource(R.drawable.ic_arrow_downward_black_24dp)
                image_view_connection_bottom.setImageResource(R.drawable.ic_arrow_downward_black_24dp)
            }
        }
    }
}