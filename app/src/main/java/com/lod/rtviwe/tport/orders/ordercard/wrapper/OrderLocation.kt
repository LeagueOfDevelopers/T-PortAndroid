package com.lod.rtviwe.tport.orders.ordercard.wrapper

import com.lod.rtviwe.tport.model.Destination

data class OrderLocation(
    val destination: Destination
) : OrderItem(OrderItemType.LOCATION)