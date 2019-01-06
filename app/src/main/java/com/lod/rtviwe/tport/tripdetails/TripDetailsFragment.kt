package com.lod.rtviwe.tport.tripdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.orders.items.OrderDestinationFirstItem
import com.lod.rtviwe.tport.orders.items.OrderDestinationItem
import com.lod.rtviwe.tport.orders.items.OrderRouteItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_routes_toolbar.*
import kotlinx.android.synthetic.main.trip_details_fragment.*

class TripDetailsFragment : BaseFragment() {

    private lateinit var fullTrip: FullTrip

    override fun getLayout() = R.layout.trip_details_fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.also {
            it.getParcelable<FullTrip>(STATE_TRIP).let { trip ->
                fullTrip = trip
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_search_routes_toolbar_label.text = getString(R.string.trip_details_toolbar_title)

        with(fullTrip.trip) {
            edit_text_toolbar_from_place.setText(placeFrom.name)
            edit_text_toolbar_to_place.setText(placeTo.name)
            edit_text_toolbar_when.setText(SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(timeTravel))
        }

        val routesAdapter = GroupAdapter<ViewHolder>()
        val routesLayoutManager = LinearLayoutManager(context)

        fullTrip.routes.forEachIndexed { index, route ->
            if (index == 0) {
                routesAdapter.add(OrderDestinationFirstItem(route.destination))
            } else {
                routesAdapter.add(OrderDestinationItem(route.destination, false))
            }

            routesAdapter.add(OrderRouteItem(route))

            if (index == fullTrip.routes!!.size - 1) {
                routesAdapter.add(OrderDestinationItem(route.destination, true))
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

        fun newInstance(fullTrip: FullTrip): TripDetailsFragment {
            val newArguments = Bundle().apply {
                putParcelable(STATE_TRIP, fullTrip)
            }

            return TripDetailsFragment().apply {
                arguments = newArguments
            }
        }

        private const val STATE_TRIP = "TRIP_STATE"
    }
}