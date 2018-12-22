package com.lod.rtviwe.tport.orders.wrappers

import com.lod.rtviwe.tport.model.Destination

data class OrderLocation(
    val destination: Destination
) : OrderItem(OrderItemType.LOCATION)