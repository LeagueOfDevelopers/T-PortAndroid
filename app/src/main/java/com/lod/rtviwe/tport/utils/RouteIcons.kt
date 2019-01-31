package com.lod.rtviwe.tport.utils

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.TransportationType

object RouteIcons {

    fun getImageResource(type: TransportationType?) = when (type) {
        TransportationType.TAXI -> R.drawable.ic_local_taxi_blue_24dp
        TransportationType.AIRPLANE -> R.drawable.ic_airplanemode_active_blue_24dp
        TransportationType.TRAIN -> R.drawable.ic_directions_railway_blue_24dp
        TransportationType.SHIP -> R.drawable.ic_directions_boat_blue_24dp
        TransportationType.FOOT -> R.drawable.ic_directions_walk_blue_24dp
        else -> R.drawable.ic_directions_walk_blue_24dp
    }

    fun getNumberDrawable(char: Char) = when (char) {
        '0' -> R.drawable._0
        '1' -> R.drawable._1
        '2' -> R.drawable._2
        '3' -> R.drawable._3
        '4' -> R.drawable._4
        '5' -> R.drawable._5
        '6' -> R.drawable._6
        '7' -> R.drawable._7
        '8' -> R.drawable._8
        '9' -> R.drawable._9
        else -> R.drawable.code_placeholder
    }
}
