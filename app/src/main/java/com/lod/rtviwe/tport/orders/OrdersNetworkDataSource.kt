package com.lod.rtviwe.tport.orders

import com.lod.rtviwe.tport.utils.CollectionJob
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent

class OrdersNetworkDataSource : OrdersDataSource, KoinComponent {

    private val collectionJob = CollectionJob()

    init {

        collectionJob.putJobs(JOB_CURRENT_ORDER, JOB_COMING_ORDERS, JOB_HISTORY_ORDERS)
    }

    override fun setCurrentOrder(callback: OrdersDataSource.OrderDataSourceCallback) {
        val job = collectionJob.getJob(JOB_CURRENT_ORDER)

        job?.let {
            it.scope.launch(job.handler) {
                // TODO
            }
        }
    }

    override fun setComingOrders(callback: OrdersDataSource.OrderDataSourceCallback) {
        val job = collectionJob.getJob(JOB_COMING_ORDERS)

        job?.let {
            it.scope.launch(job.handler) {
                // TODO
            }
        }
    }

    override fun setHistoryOrders(callback: OrdersDataSource.OrderDataSourceCallback) {
        val job = collectionJob.getJob(JOB_HISTORY_ORDERS)

        job?.let {
            it.scope.launch(job.handler) {
                // TODO
            }
        }
    }

    override fun clear() {
        collectionJob.clear()
    }

    companion object {

        private const val JOB_CURRENT_ORDER = "CURRENT_ORDER_JOB"
        private const val JOB_COMING_ORDERS = "COMING_ORDERS_JOB"
        private const val JOB_HISTORY_ORDERS = "HISTORY_ORDERS_JOB"

    }
}