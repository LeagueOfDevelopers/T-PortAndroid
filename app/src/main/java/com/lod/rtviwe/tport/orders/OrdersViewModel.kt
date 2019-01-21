package com.lod.rtviwe.tport.orders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.orders.items.ComingOrderCardItem
import com.lod.rtviwe.tport.orders.items.CurrentOrderCardItem
import com.lod.rtviwe.tport.orders.items.HeaderItem
import com.lod.rtviwe.tport.orders.items.HistoryOrderCardItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class OrdersViewModel(private val app: Application) : AndroidViewModel(app) {

    private val job = Job()

    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun observeAdapter(owner: LifecycleOwner, ordersAdapter: GroupAdapter<ViewHolder>) {
        MockTrips.getItems().observe(owner, Observer { it ->
            ordersAdapter.apply {
                add(CurrentOrderCardItem(it[0]).apply {
                    add(HeaderItem(app.getString(R.string.current_order)))
                })
                add(HeaderItem(app.getString(R.string.coming_order)))
                addAll(it.map { ComingOrderCardItem(it) })
                add(HeaderItem(app.getString(R.string.history)))
                addAll(it.map { HistoryOrderCardItem(it) })
            }
        })
    }
}