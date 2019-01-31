package com.lod.rtviwe.tport.orders

import com.lod.rtviwe.tport.data.MockTrips

class OrdersMockDataSource : OrdersDataSource {

    override fun setCurrentOrder(callback: OrdersDataSource.OrderDataSourceCallback) {
        callback.getOrders(MockTrips.getItems())
    }

    override fun setComingOrders(callback: OrdersDataSource.OrderDataSourceCallback) {
        callback.getOrders(MockTrips.getItems())
    }

    override fun setHistoryOrders(callback: OrdersDataSource.OrderDataSourceCallback) {
        callback.getOrders(MockTrips.getItems())
    }

    override fun clear() {}
}