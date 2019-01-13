package com.lod.rtviwe.tport.tripdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.orders.items.OrderDestinationFirstItem
import com.lod.rtviwe.tport.orders.items.OrderDestinationItem
import com.lod.rtviwe.tport.orders.items.OrderRouteItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_routes_toolbar.*
import kotlinx.android.synthetic.main.trip_details_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class TripDetailsFragment : BaseFragment() {

    private lateinit var trip: Trip

    override fun getLayout() = R.layout.trip_details_fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.also {
            it.getParcelable<Trip>(STATE_TRIP)?.let { trip ->
                this.trip = trip
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_search_routes_toolbar_label.text = getString(R.string.trip_details_toolbar_title)

        edit_text_toolbar_from_place.setText(trip.destination.fromPlace.name)
        edit_text_toolbar_to_place.setText(trip.destination.toPlace.name)
        edit_text_toolbar_when.setText(
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(trip.routes[0].departureDate)
        )

        val routesAdapter = GroupAdapter<ViewHolder>()
        val routesLayoutManager = LinearLayoutManager(context)

        trip.routes.forEachIndexed { index, route ->
            if (index == 0) {
                routesAdapter.add(OrderDestinationFirstItem(route.destination.fromPlace))
            } else {
                routesAdapter.add(OrderDestinationItem(route, false))
            }

            routesAdapter.add(OrderRouteItem(route))

            if (index == trip.routes.size - 1) {
                routesAdapter.add(OrderDestinationItem(route, true))
            }
        }

        recycler_view_order_routes.apply {
            layoutManager = routesLayoutManager
            adapter = routesAdapter
        }

        fab_book_trip.setOnClickListener {

        }

        image_button_change.setOnClickListener {
            swapDestinations()
        }
    }

    private fun swapDestinations() {
        val temp = edit_text_toolbar_from_place.text
        edit_text_toolbar_from_place.text = edit_text_toolbar_to_place.text
        edit_text_toolbar_to_place.text = temp
    }

    companion object {

        fun newInstance(trip: Trip): TripDetailsFragment {
            val newArguments = Bundle().apply {
                putParcelable(STATE_TRIP, trip)
            }

            return TripDetailsFragment().apply {
                arguments = newArguments
            }
        }

        private const val STATE_TRIP = "TRIP_STATE"
    }
}