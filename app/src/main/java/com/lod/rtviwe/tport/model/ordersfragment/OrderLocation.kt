package com.lod.rtviwe.tport.model.ordersfragment

import com.lod.rtviwe.tport.model.Location

data class OrderLocation(
    val location: Location
) : OrderItem(OrderItemType.LOCATION)