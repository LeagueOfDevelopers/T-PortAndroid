package com.lod.rtviwe.tport.orders.items

import androidx.core.widget.TextViewCompat
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Route
import com.lod.rtviwe.tport.utils.RouteIcons
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.order_route_item.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

class OrderRouteItem(private val route: Route) : Item() {

    override fun getLayout() = R.layout.order_route_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val imageResourceToSet = RouteIcons.getImageResource(route.transport.type)
        viewHolder.image_view_route_type.setImageResource(imageResourceToSet)

        viewHolder.text_view_route_name.text = route.transport.name
        viewHolder.text_view_route_cost.text =
                String.format(viewHolder.containerView.context.getString(R.string.money), route.cost.roundToInt())

        // TODO make cool extension function if needed
        val diff = route.arrivalDate.time - route.departureDate.time
        val hours = TimeUnit.MILLISECONDS.toHours(diff)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diff) - hours * 60

        viewHolder.text_view_coming_order_is_paid.text = String.format(
            viewHolder.containerView.context.getString(R.string.time_in_travel),
//            DateFormat.getTimeInstance().format(route.departureDate/*, route.arrivalDate*/)
            "${hours}ч ${minutes}мин"
        )

        viewHolder.text_view_is_route_paid.text =
                if (route.isPaid) {
                    TextViewCompat.setTextAppearance(viewHolder.text_view_is_route_paid, R.style.TextViewIsPaid)
                    viewHolder.containerView.context.getString(R.string.is_paid)
                } else {
                    TextViewCompat.setTextAppearance(viewHolder.text_view_is_route_paid, R.style.TextViewIsNotPaid)
                    viewHolder.containerView.context.getString(R.string.not_paid)
                }

        viewHolder.image_view_arrow.setOnClickListener {

        }
    }
}