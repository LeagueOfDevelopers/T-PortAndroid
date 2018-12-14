package com.lod.rtviwe.tport.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.searchrouteitem.SearchRouteItem
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderSearchRouteItem

class SearchRoutesAdapter(
    private val context: Context?,
    private var data: List<SearchRouteItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderSearchRouteItem(
            LayoutInflater.from(context)
                .inflate(R.layout.search_route_item, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderSearchRouteItem).bind(data[position])
    }

    fun setData(newData: List<SearchRouteItem>) {
        data = newData
        notifyDataSetChanged()
    }
}