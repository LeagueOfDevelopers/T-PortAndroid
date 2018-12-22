package com.lod.rtviwe.tport.orders.ordercard.wrapper

import com.lod.rtviwe.tport.model.Route

data class OrderRoute(
    val route: Route
) : OrderItem(OrderItemType.ROUTE)