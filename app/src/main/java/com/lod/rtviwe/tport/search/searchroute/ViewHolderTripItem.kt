package com.lod.rtviwe.tport.search.searchroute

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.listeners.TripClickedListener
import com.lod.rtviwe.tport.model.Trip
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_route_card.*
import java.text.SimpleDateFormat
import java.util.*

class ViewHolderTripItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private lateinit var tripClickedListener: TripClickedListener

    fun bind(tripItem: Trip) {
        when (containerView.context) {
            is TripClickedListener -> tripClickedListener = containerView.context as TripClickedListener
            else -> throw ClassCastException("${containerView.context} does not implements SearchListener")
        }

        text_view_route_time.text = SimpleDateFormat("hh:mm", Locale.getDefault()).format(tripItem.timeTravel)
        text_view_search_route_cost.text =
                String.format(containerView.context.getString(R.string.money), tripItem.cost)

        val searchRouteItemAdapter =
            SearchRouteItemAdapter(containerView.context, listOf())
        val searchRoutesLayoutManager = LinearLayoutManager(containerView.context, RecyclerView.HORIZONTAL, false)

        searchRouteItemAdapter.setData(tripItem.routes)

        recycler_view_routes_in_item.apply {
            adapter = searchRouteItemAdapter
            layoutManager = searchRoutesLayoutManager
        }

        card_trip_item.setOnClickListener {
            tripClickedListener.openTripDetailFragment()
        }

        recycler_view_routes_in_item.setOnClickListener {
            tripClickedListener.openTripDetailFragment()
        }
    }
}