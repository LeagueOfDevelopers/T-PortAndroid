package com.lod.rtviwe.tport.ui.viewholder.orderfragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.model.ordersfragment.OrderLocation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.order_location_item.*
import java.text.SimpleDateFormat
import java.util.*

class ViewHolderOrderLocation(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(orderLocation: OrderLocation, isLast: Boolean) {
        text_view_location.text = if (isLast)
            orderLocation.destination.placeTo
        else
            orderLocation.destination.placeFrom

        text_view_arrival_date.text =
                "Время прибытия: ${SimpleDateFormat(
                    "hh:mm",
                    Locale.getDefault()
                ).format(orderLocation.destination.arrivalDate)}"
    }
}