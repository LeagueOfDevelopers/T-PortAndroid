package com.lod.rtviwe.tport.orders.items

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.searchtrip.items.RouteItem
import com.lod.rtviwe.tport.tripdetails.TripDetailsFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.history_order_item.*

class HistoryOrderCardItem(private val trip: Trip) : Item() {

    private lateinit var navController: NavController

    override fun getLayout() = R.layout.history_order_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        navController =
                Navigation.findNavController(viewHolder.containerView.context as Activity, R.id.nav_host_fragment)

        with(trip.destination) {
            viewHolder.text_view_from_place.text = fromPlace.name
            viewHolder.text_view_to_place.text = toPlace.name
        }

        val searchRouteItemAdapter = GroupAdapter<ViewHolder>()
        val searchRoutesLayoutManager =
            LinearLayoutManager(viewHolder.containerView.context, RecyclerView.HORIZONTAL, false)

        trip.routes.forEachIndexed { index, route ->
            val isFirst = index == 0
            val isLast = index == trip.routes.size - 1
            searchRouteItemAdapter.add(Section(RouteItem(route, isFirst, isLast)))
        }

        viewHolder.recycler_view_routes.apply {
            adapter = searchRouteItemAdapter
            layoutManager = searchRoutesLayoutManager
        }

        viewHolder.card_history_item.setOnClickListener {
            val bundle = Bundle().apply { putParcelable(TripDetailsFragment.ARGUMENT_TRIP, trip) }
            navController.navigate(R.id.action_ordersFragment_to_tripDetailsFragment, bundle)
        }
    }
}