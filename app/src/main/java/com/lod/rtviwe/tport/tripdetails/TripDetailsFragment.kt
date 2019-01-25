package com.lod.rtviwe.tport.tripdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.model.Trip
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_trips_toolbar_filter.*
import kotlinx.android.synthetic.main.trip_details_fragment.*
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class TripDetailsFragment : BaseFragment() {

    private val routesAdapter = GroupAdapter<ViewHolder>()
    private lateinit var trip: Trip
    private lateinit var routesLayoutManager: LinearLayoutManager
    private lateinit var routesRecyclerView: RecyclerView

    private val tripDetailsViewModel by inject<TripDetailsViewModel>()

    override fun getLayout() = R.layout.trip_details_fragment

    override fun scrollToTop() {
        routesLayoutManager.smoothScrollToPosition(routesRecyclerView, RecyclerView.State(), 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.also {
            it.getParcelable<Trip>(ARGUMENT_TRIP)?.let { trip -> this.trip = trip }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_search_routes.text = getString(R.string.trip_details_toolbar_title)

        edit_text_from_place.setText(trip.destination.fromPlace.name)
        edit_text_to_place.setText(trip.destination.toPlace.name)
        edit_text_travel_time.setText(
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(trip.routes[0].departureDate)
        )

        routesLayoutManager = LinearLayoutManager(context)

        tripDetailsViewModel.populateAdapter(routesAdapter, trip)

        recycler_view_routes.apply {
            layoutManager = routesLayoutManager
            adapter = routesAdapter
        }

        fab_book_trip.setOnClickListener {

        }
    }

    companion object {

        const val ARGUMENT_TRIP = "TRIP_ARGUMENT"
    }
}