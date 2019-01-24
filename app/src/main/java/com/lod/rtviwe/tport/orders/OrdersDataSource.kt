package com.lod.rtviwe.tport.orders

import com.lod.rtviwe.tport.model.Trip

interface OrdersDataSource {

    interface OrderDataSourceCallback {

        fun getOrders(orders: List<Trip>)
    }

    fun setCurrentOrder(callback: OrderDataSourceCallback)

    fun setComingOrders(callback: OrderDataSourceCallback)

    fun setHistoryOrders(callback: OrderDataSourceCallback)

    fun clear()
}