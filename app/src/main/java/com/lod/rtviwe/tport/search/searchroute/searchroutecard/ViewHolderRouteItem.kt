package com.lod.rtviwe.tport.search.searchroute.searchroutecard

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Route
import com.lod.rtviwe.tport.utils.RouteIcons
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_route_item.*

class ViewHolderRouteItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    @SuppressLint("SetTextI18n")
    fun bind(routeItem: Route, isFirst: Boolean, isLast: Boolean) {
        val imageResourceToSet = RouteIcons.getImageResource(routeItem.type)
        image_view_route_item.setImageResource(imageResourceToSet)

        if (!isFirst) {
            image_view_connection_start.background =
                    containerView.context.getDrawable(R.drawable.connection_rectangle_horizontal_start)
        } else {
            image_view_connection_start.visibility = View.GONE
        }

        if (!isLast) {
            image_view_connection_end.background =
                    containerView.context.getDrawable(R.drawable.connection_rectangle_horizontal_end)
        } else {
            image_view_connection_end.visibility = View.GONE
        }

        text_view_route_type.text = routeItem.vehicleName
        text_view_route_cost.text = "${routeItem.cost} \u20BD" // \u20BD - ₽
    }
}