package com.lod.rtviwe.tport.ui.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.ui.adapter.SearchRouteItemAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_route_card.*

class ViewHolderRouteCardItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    @SuppressLint("SetTextI18n")
    fun bind(tripItem: Trip) {
        text_view_route_time.text = tripItem.timeTravel.toString()
        text_view_search_route_cost.text = "${tripItem.cost} \u20BD" // \u20BD - ₽

        val searchRouteItemAdapter = SearchRouteItemAdapter(containerView.context, listOf())
        val searchRoutesLayoutManager = LinearLayoutManager(containerView.context, RecyclerView.HORIZONTAL, false)

        searchRouteItemAdapter.setData(tripItem.items)

        recycler_view_routes_in_item.apply {
            adapter = searchRouteItemAdapter
            layoutManager = searchRoutesLayoutManager
        }
    }
}