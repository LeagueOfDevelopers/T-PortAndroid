package com.lod.rtviwe.tport.ui.adapter.order

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.ordersfragment.OrderItem
import com.lod.rtviwe.tport.model.ordersfragment.OrderItemType
import com.lod.rtviwe.tport.model.ordersfragment.OrderLocation
import com.lod.rtviwe.tport.model.ordersfragment.OrderRoute
import com.lod.rtviwe.tport.ui.viewholder.orderfragment.ViewHolderOrderLocation
import com.lod.rtviwe.tport.ui.viewholder.orderfragment.ViewHolderOrderRoute

class OrderRoutesAdapter(
    private val context: Context?,
    private var orderItemsList: List<OrderItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = orderItemsList.size

    override fun getItemViewType(position: Int): Int = orderItemsList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        OrderItemType.LOCATION.ordinal -> ViewHolderOrderLocation(
            LayoutInflater.from(context)
                .inflate(R.layout.order_location_item, parent, false)
        )
        OrderItemType.ROUTE.ordinal -> ViewHolderOrderRoute(
            LayoutInflater.from(context)
                .inflate(R.layout.order_route_item, parent, false)
        )
        else -> throw RuntimeException("Unknown route type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            OrderItemType.LOCATION.ordinal -> {
                val isLast = (position == (orderItemsList.size - 1))
                (holder as ViewHolderOrderLocation).bind(orderItemsList[position] as OrderLocation, isLast)
            }
            OrderItemType.ROUTE.ordinal ->
                (holder as ViewHolderOrderRoute).bind(orderItemsList[position] as OrderRoute)
        }
    }

    fun setData(newData: List<OrderItem>) {
        orderItemsList = newData
        notifyDataSetChanged()
    }
}