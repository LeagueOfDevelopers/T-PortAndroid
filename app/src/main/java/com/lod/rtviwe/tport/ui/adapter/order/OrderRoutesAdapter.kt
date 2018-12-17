package com.lod.rtviwe.tport.ui.adapter.order

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Route
import com.lod.rtviwe.tport.model.ordersfragment.OrderItemType
import com.lod.rtviwe.tport.ui.viewholder.orderfragment.ViewHolderOrderLocation
import com.lod.rtviwe.tport.ui.viewholder.orderfragment.ViewHolderOrderRoute
import com.lod.rtviwe.tport.ui.viewholder.searchfragment.ViewHolderRouteItem

class OrderRoutesAdapter(
    private val context: Context?,
    private var routesList: List<Route>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = routesList.size

    override fun getItemViewType(position: Int): Int = routesList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        OrderItemType.LOCATION.ordinal -> ViewHolderOrderLocation(
            LayoutInflater.from(context)
                .inflate(R.layout.order_location_item, parent, false)
        )
        OrderItemType.ROUTE.ordinal -> ViewHolderRouteItem(
            LayoutInflater.from(context)
                .inflate(R.layout.order_route_item, parent, false)
        )
        else -> throw RuntimeException("Unknown boxItem type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            OrderItemType.LOCATION.ordinal -> (holder as ViewHolderOrderLocation).bind(routesList[position].location)
            OrderItemType.ROUTE.ordinal -> (holder as ViewHolderOrderRoute).bind(routesList[position])
        }
    }

    fun setData(newData: List<Route>) {
        routesList = newData
        notifyDataSetChanged()
    }
}