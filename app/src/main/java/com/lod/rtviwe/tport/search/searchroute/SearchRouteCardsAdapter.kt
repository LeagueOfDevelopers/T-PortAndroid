package com.lod.rtviwe.tport.search.searchroute

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.FullTrip

class SearchRouteCardsAdapter(
    private val context: Context?,
    private var tripsList: List<FullTrip>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = tripsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderTripItem(
            LayoutInflater.from(context)
                .inflate(R.layout.search_route_card, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderTripItem).bind(tripsList[position])
    }

    fun setData(newData: List<FullTrip>) {
        tripsList = newData
        notifyDataSetChanged()
    }
}