package com.lod.rtviwe.tport.search.searchroute

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Route

class SearchRouteItemAdapter(
    private val context: Context?,
    private var routesList: List<Route>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = routesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderRouteItem(
            LayoutInflater.from(context)
                .inflate(R.layout.search_route_item, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val isFirst = (position == 0)
        val isLast = (position == (routesList.size - 1))
        (holder as ViewHolderRouteItem).bind(routesList[position], isFirst, isLast)
    }

    fun setData(newData: List<Route>) {
        routesList = newData
        notifyDataSetChanged()
    }
}