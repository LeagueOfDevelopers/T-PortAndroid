package com.lod.rtviwe.tport.orders

import com.lod.rtviwe.tport.data.MockTrips
import kotlinx.coroutines.*
import timber.log.Timber

class OrdersNetworkDataSource : OrdersDataSource {

    private val jobCurrentOrder = Job()
    private val jobComingOrders = Job()
    private val jobHistoryOrders = Job()

    private val scopeCurrentOrder = CoroutineScope(Dispatchers.Main + jobCurrentOrder)
    private val scopeComingOrders = CoroutineScope(Dispatchers.Main + jobComingOrders)
    private val scopeHistoryOrders = CoroutineScope(Dispatchers.Main + jobHistoryOrders)

    private val handlerCurrentOrder = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting current order: $exception")
    }

    private val handlerComingOrders = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting coming orders: $exception")
    }

    private val handlerHistoryOrders = CoroutineExceptionHandler { _, exception ->
        Timber.e("Error while getting history orders: $exception")
    }

    override fun setCurrentOrder(callback: OrdersDataSource.OrderDataSourceCallback) {
        scopeCurrentOrder.launch(handlerCurrentOrder) {
            callback.getOrders(MockTrips.getItems())
        }
    }

    override fun setComingOrders(callback: OrdersDataSource.OrderDataSourceCallback) {
        scopeComingOrders.launch(handlerComingOrders) {
            callback.getOrders(MockTrips.getItems())
        }
    }

    override fun setHistoryOrders(callback: OrdersDataSource.OrderDataSourceCallback) {
        scopeHistoryOrders.launch(handlerHistoryOrders) {
            callback.getOrders(MockTrips.getItems())
        }
    }

    override fun clear() {
        jobCurrentOrder.cancel()
        jobComingOrders.cancel()
        jobHistoryOrders.cancel()
    }
}