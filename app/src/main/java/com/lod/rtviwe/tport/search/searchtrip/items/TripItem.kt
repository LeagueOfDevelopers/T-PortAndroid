package com.lod.rtviwe.tport.search.searchtrip.items

import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.searchtrip.SearchTripClickedListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_trip_card.*
import java.text.SimpleDateFormat
import java.util.*

class TripItem(private val trip: Trip) : Item() {

    private lateinit var searchTripClickedListener: SearchTripClickedListener

    override fun getLayout() = R.layout.search_trip_card

    override fun bind(viewHolder: ViewHolder, position: Int) {
        when (viewHolder.containerView.context) {
            is SearchTripClickedListener -> searchTripClickedListener =
                    viewHolder.containerView.context as SearchTripClickedListener
            else -> throw ClassCastException("${viewHolder.containerView.context} does not implement SearchListener")
        }

        viewHolder.text_view_route_time.text =
                SimpleDateFormat("hh:mm", Locale.getDefault()).format(trip.duration)
        viewHolder.text_view_search_route_cost.text =
                String.format(viewHolder.containerView.context.getString(R.string.money), trip.cost)

        val searchRouteItemAdapter = GroupAdapter<ViewHolder>()
        val searchRoutesLayoutManager =
            LinearLayoutManager(viewHolder.containerView.context, RecyclerView.HORIZONTAL, false)

        trip.routes.forEachIndexed { index, route ->
            val isFirst = index == 0
            val isLast = index == trip.routes.size - 1
            searchRouteItemAdapter.add(Section(RouteItem(route, isFirst, isLast)))
        }

        viewHolder.recycler_view_routes_in_item.apply {
            adapter = searchRouteItemAdapter
            layoutManager = searchRoutesLayoutManager
        }

        viewHolder.card_trip_item.setOnClickListener {
            searchTripClickedListener.openTripDetailsFragmentFromSearch(trip)
        }

        viewHolder.recycler_view_routes_in_item.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                searchTripClickedListener.openTripDetailsFragmentFromSearch(trip)
            }
            true
        }
    }
}