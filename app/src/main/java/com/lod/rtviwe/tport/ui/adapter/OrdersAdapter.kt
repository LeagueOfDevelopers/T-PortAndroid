package com.lod.rtviwe.tport.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderMyOrderItem

class OrdersAdapter(
    private val context: Context?,
    private var tripsList: List<Trip>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = tripsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderMyOrderItem(
            LayoutInflater.from(context)
                .inflate(R.layout.my_order_route_item, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderMyOrderItem).bind(tripsList[position])
    }

    fun setData(newData: List<Trip>) {
        tripsList = newData
        notifyDataSetChanged()
    }
}