package com.lod.rtviwe.tport.orders

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.orders.items.ComingOrderCardItem
import com.lod.rtviwe.tport.orders.items.CurrentOrderCardItem
import com.lod.rtviwe.tport.orders.items.HeaderItem
import com.lod.rtviwe.tport.orders.items.HistoryOrderCardItem
import com.lod.rtviwe.tport.tripdetails.TripDetailsFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class OrdersViewModel(
    private val app: Application,
    private val ordersDataSource: OrdersDataSource
) : AndroidViewModel(app) {

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
                    add(HeaderItem(app.getString(R.string.history_order)))
                    addAll(orders.map { HistoryOrderCardItem(it) })
                }
            }
        })
    }

    fun navigateToTripDetails(navController: NavController, trip: Trip) {
        val bundle = Bundle().apply { putParcelable(TripDetailsFragment.ARGUMENT_TRIP, trip) }
        navController.navigate(R.id.action_ordersFragment_to_tripDetailsFragment, bundle)
    }
}