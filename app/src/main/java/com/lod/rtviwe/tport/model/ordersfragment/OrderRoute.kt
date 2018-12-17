package com.lod.rtviwe.tport.model.ordersfragment

import com.lod.rtviwe.tport.model.Route

data class OrderRoute(
    val route: Route
) : OrderItem(OrderItemType.LOCATION)