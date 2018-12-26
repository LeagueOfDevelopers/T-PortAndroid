package com.lod.rtviwe.tport.search.searchroute

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.listeners.SearchTripClickedListener
import com.lod.rtviwe.tport.model.FullTrip
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_route_card.*
import java.text.SimpleDateFormat
import java.util.*

class ViewHolderTripItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private lateinit var searchTripClickedListener: SearchTripClickedListener

    init {

        when (containerView.context) {
            is SearchTripClickedListener -> searchTripClickedListener =
                    containerView.context as SearchTripClickedListener
            else -> throw ClassCastException("${containerView.context} does not implements SearchListener")
        }
    }

    fun bind(tripItem: FullTrip) {
        text_view_route_time.text = SimpleDateFormat("hh:mm", Locale.getDefault()).format(tripItem.trip.timeTravel)
        text_view_search_route_cost.text =
                String.format(containerView.context.getString(R.string.money), tripItem.trip.cost)

        val searchRouteItemAdapter =
            SearchRouteItemAdapter(containerView.context, listOf())
        val searchRoutesLayoutManager = LinearLayoutManager(containerView.context, RecyclerView.HORIZONTAL, false)

        tripItem.routes?.let {
            searchRouteItemAdapter.setData(it)
        }

        recycler_view_routes_in_item.apply {
            adapter = searchRouteItemAdapter
            layoutManager = searchRoutesLayoutManager
        }

        card_trip_item.setOnClickListener {
            searchTripClickedListener.openTripDetailFragmentFromSearch()
        }

        recycler_view_routes_in_item.setOnClickListener {
            searchTripClickedListener.openTripDetailFragmentFromSearch()
        }
    }
}