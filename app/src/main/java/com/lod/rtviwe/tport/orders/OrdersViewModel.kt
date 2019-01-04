package com.lod.rtviwe.tport.orders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.data.MockTrips
import com.lod.rtviwe.tport.orders.decorators.OrderCardItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class OrdersViewModel(app: Application) : AndroidViewModel(app) {

    private val job = Job()

    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun observeAdapter(owner: LifecycleOwner, ordersAdapter: GroupAdapter<ViewHolder>) {
        MockTrips.getItems().observe(owner, Observer { it ->
            ordersAdapter.addAll(it.map { Section(OrderCardItem(it)) })
        })
    }
}