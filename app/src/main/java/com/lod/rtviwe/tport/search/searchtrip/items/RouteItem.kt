package com.lod.rtviwe.tport.search.searchtrip.items

import android.view.View
import android.view.ViewGroup
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Route
import com.lod.rtviwe.tport.utils.RouteIcons
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.trip_route_item.*
import kotlin.math.roundToInt

class RouteItem(private val route: Route, private val isFirst: Boolean, private val isLast: Boolean) : Item() {

    override fun getLayout() = R.layout.trip_route_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val imageResourceToSet = RouteIcons.getImageResource(route.transport.type)
        viewHolder.image_view_route_item.setImageResource(imageResourceToSet)

        if (!isFirst) {
            viewHolder.image_view_connection_end.background =
                    viewHolder.containerView.context.getDrawable(R.drawable.connection_rectangle_horizontal_start)
        } else {
            viewHolder.image_view_connection_end.visibility = View.INVISIBLE
        }

        if (!isLast) {
            viewHolder.image_view_connection_start.background =
                    viewHolder.containerView.context.getDrawable(R.drawable.connection_rectangle_horizontal_end)
        } else {
            viewHolder.image_view_connection_start.visibility = View.GONE
            val imageViewRoute = viewHolder.image_view_route_item
            val params = imageViewRoute.layoutParams as ViewGroup.MarginLayoutParams
            params.marginEnd = 80
            imageViewRoute.requestLayout()
        }

        viewHolder.text_view_route_type.text = route.transport.name
        viewHolder.text_view_route_cost.text =
                String.format(viewHolder.containerView.context.getString(R.string.money), route.cost.roundToInt())
    }
}