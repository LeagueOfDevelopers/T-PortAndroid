package com.lod.rtviwe.tport.search.searchtrip

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.network.searchTrips.TripsRequest
import com.lod.rtviwe.tport.search.SearchListener
import com.lod.rtviwe.tport.search.searchbox.DestinationWord
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_routes_toolbar.*
import kotlinx.android.synthetic.main.search_trips_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchTripsFragment : BaseFragment() {

    override fun getLayout() = R.layout.search_trips_fragment

    private val searchRoutesViewModel by viewModel<SearchTripsViewModel>()
    private var searchRouteCardsAdapter = GroupAdapter<ViewHolder>()

    private lateinit var searchTripClickedListener: SearchTripClickedListener
    private lateinit var searchListener: SearchListener
    private lateinit var searchRoutesLayoutManager: LinearLayoutManager
    private lateinit var searchRoutesRecyclerView: RecyclerView

    private var fromPlace = DestinationWord()
    private var toPlace = DestinationWord()
    private var travelTime = ""

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is SearchListener -> searchListener = context
            else -> throw ClassCastException("$context does not implement SearchListener")
        }

        when (context) {
            is SearchTripClickedListener -> searchTripClickedListener = context
            else -> throw ClassCastException("$context does not implement SearchTripClickedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.also {
            it.getParcelable<DestinationWord>(STATE_FROM_PLACE)?.let { place -> fromPlace = place }
            it.getParcelable<DestinationWord>(STATE_TO_PLACE)?.let { place -> toPlace = place }
            it.getString(STATE_TRAVEL_TIME)?.let { time -> travelTime = time }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_search_routes_toolbar_label.text = getString(R.string.search_routes_toolbar_title)

        edit_text_toolbar_from_place.setText(fromPlace.name)
        edit_text_toolbar_to_place.setText(toPlace.name)
        edit_text_toolbar_when.setText(travelTime)

        searchRoutesLayoutManager = LinearLayoutManager(context)

        val tripsRequest = TripsRequest(fromPlace.code, toPlace.code, travelTime)
        searchRoutesViewModel.observeAdapter(this, searchRouteCardsAdapter, tripsRequest)

        searchRoutesRecyclerView = recycler_view_search_routes.apply {
            adapter = searchRouteCardsAdapter
            layoutManager = searchRoutesLayoutManager
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

        fun newInstance(fromPlace: DestinationWord, toPlace: DestinationWord, travelTime: String): SearchTripsFragment {
            val newArguments = Bundle().apply {
                putParcelable(STATE_FROM_PLACE, fromPlace)
                putParcelable(STATE_TO_PLACE, toPlace)
                putString(STATE_TRAVEL_TIME, travelTime)
            }

            return SearchTripsFragment().apply {
                arguments = newArguments
            }
        }

        private const val STATE_FROM_PLACE = "FROM_PLACE_STATE"
        private const val STATE_TO_PLACE = "FROM_TO_STATE"
        private const val STATE_TRAVEL_TIME = "TRAVEL_TIME_STATE"
    }
}