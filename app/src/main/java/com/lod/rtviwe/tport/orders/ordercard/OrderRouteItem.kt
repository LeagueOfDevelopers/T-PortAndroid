package com.lod.rtviwe.tport.orders.ordercard

import androidx.core.widget.TextViewCompat
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.listeners.OrderTripClickedListener
import com.lod.rtviwe.tport.model.Route
import com.lod.rtviwe.tport.utils.RouteIcons
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.order_route_item.*
import java.text.SimpleDateFormat
import java.util.*

class OrderRouteItem(private val route: Route) : Item() {

    private lateinit var orderTripClickedListener: OrderTripClickedListener

    override fun getLayout() = R.layout.order_route_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        when (viewHolder.containerView.context) {
            is OrderTripClickedListener -> orderTripClickedListener =
                    viewHolder.containerView.context as OrderTripClickedListener
            else -> throw ClassCastException("${viewHolder.containerView.context} does not implements SearchListener")
        }

        val imageResourceToSet = RouteIcons.getImageResource(route.type)
        viewHolder.image_view_route_type.setImageResource(imageResourceToSet)

        viewHolder.text_view_route_name.text = route.vehicleName
        viewHolder.text_view_route_cost.text =
                String.format(viewHolder.containerView.context.getString(R.string.money), route.cost)

        viewHolder.text_view_route_time_in_trip.text = String.format(
            viewHolder.containerView.context.getString(R.string.time_in_travel),
            SimpleDateFormat("hh:mm", Locale.getDefault()).format(route.destination.arrivalDate)
        )

        viewHolder.text_view_is_route_paid.text =
                if (route.isPaid) {
                    TextViewCompat.setTextAppearance(viewHolder.text_view_is_route_paid, R.style.TextViewIsPaid)
                    viewHolder.containerView.context.getString(R.string.is_paid)
                } else {
                    TextViewCompat.setTextAppearance(viewHolder.text_view_is_route_paid, R.style.TextViewIsNotPaid)
                    viewHolder.containerView.context.getString(R.string.not_paid)
                }

        viewHolder.card_trip_route_item.setOnClickListener {
            orderTripClickedListener.openTripDetailFragmentFromOrder()
        }
    }
}