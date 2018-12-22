package com.lod.rtviwe.tport.orders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip

class OrdersAdapter(
    private val context: Context?,
    private var tripsList: List<Trip>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = tripsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderOrderItem(
            LayoutInflater.from(context)
                .inflate(R.layout.order_item, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderOrderItem).bind(tripsList[position])
    }

    fun setData(newData: List<Trip>) {
        tripsList = newData
        notifyDataSetChanged()
    }
}