package com.lod.rtviwe.tport.ui.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Route
import com.lod.rtviwe.tport.model.RouteType
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_route_item.*

class ViewHolderRouteItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    @SuppressLint("SetTextI18n")
    fun bind(routeItem: Route, isFirst: Boolean, isLast: Boolean) {
        val imageResourceToSet = getImageResource(routeItem.type)
        image_view_route_item.setImageResource(imageResourceToSet)

        if (!isFirst) {
            image_view_connection_start.setImageResource(R.drawable.ic_trending_flat_black_24dp)
        }

        if (!isLast) {
            image_view_connection_end.setImageResource(R.drawable.ic_trending_flat_black_24dp)
        }

        text_view_route_type.text = routeItem.text
        text_view_route_cost.text = "${routeItem.cost} \u20BD" // \u20BD - ₽
    }

    private fun getImageResource(type: RouteType) = when (type) {
        RouteType.TAXI -> R.drawable.ic_local_taxi_blue_24dp
        RouteType.AIRPLANE -> R.drawable.ic_airplanemode_active_blue_24dp
        RouteType.TRAIN -> R.drawable.ic_directions_railway_blue_24dp
        RouteType.SHIP -> R.drawable.ic_directions_boat_blue_24dp
        RouteType.FOOT -> R.drawable.ic_directions_walk_blue_24dp
    }
}