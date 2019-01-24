package com.lod.rtviwe.tport.orders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.orders.items.ComingOrderCardItem
import com.lod.rtviwe.tport.orders.items.CurrentOrderCardItem
import com.lod.rtviwe.tport.orders.items.HeaderItem
import com.lod.rtviwe.tport.orders.items.HistoryOrderCardItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class OrdersViewModel(private val app: Application) : AndroidViewModel(app), KoinComponent {

    private val ordersDataSource: OrdersNetworkDataSource by inject()

    override fun onCleared() {
        super.onCleared()
        ordersDataSource.clear()
    }

    fun observeAdapter(ordersAdapter: GroupAdapter<ViewHolder>) {
        ordersDataSource.setCurrentOrder(object : OrdersDataSource.OrderDataSourceCallback {
            override fun getOrders(orders: List<Trip>) {
                with(ordersAdapter) {
                    add(HeaderItem(app.getString(R.string.current_order)))
                    add(CurrentOrderCardItem(orders[0]))
                }
            }
        })

        ordersDataSource.setComingOrders(object : OrdersDataSource.OrderDataSourceCallback {
            override fun getOrders(orders: List<Trip>) {
                with(ordersAdapter) {
                    add(HeaderItem(app.getString(R.string.coming_order)))
                    addAll(orders.map { ComingOrderCardItem(it) })
                }
            }
        })

        ordersDataSource.setHistoryOrders(object : OrdersDataSource.OrderDataSourceCallback {
            override fun getOrders(orders: List<Trip>) {
                with(ordersAdapter) {
                    add(HeaderItem(app.getString(R.string.history)))
                    addAll(orders.map { HistoryOrderCardItem(it) })
                }
            }
        })
    }
}