package com.lod.rtviwe.tport.ui.viewholder.orderfragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.model.ordersfragment.OrderLocation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.order_location_item.*

class ViewHolderOrderLocation(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(orderLocation: OrderLocation, isLast: Boolean) {
        text_view_location.text = if (isLast)
            orderLocation.location.placeTo
        else
            orderLocation.location.placeFrom
    }
}