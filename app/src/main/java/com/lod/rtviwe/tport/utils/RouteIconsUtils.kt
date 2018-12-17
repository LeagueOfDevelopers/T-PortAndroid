package com.lod.rtviwe.tport.utils

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.RouteType

object RouteIconsUtils {

    fun getImageResource(type: RouteType) = when (type) {
        RouteType.TAXI -> R.drawable.ic_local_taxi_blue_24dp
        RouteType.AIRPLANE -> R.drawable.ic_airplanemode_active_blue_24dp
        RouteType.TRAIN -> R.drawable.ic_directions_railway_blue_24dp
        RouteType.SHIP -> R.drawable.ic_directions_boat_blue_24dp
        RouteType.FOOT -> R.drawable.ic_directions_walk_blue_24dp
    }
}
