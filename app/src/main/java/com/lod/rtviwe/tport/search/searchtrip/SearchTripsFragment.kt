package com.lod.rtviwe.tport.search.searchtrip

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.network.searchTrips.TripsRequest
import com.lod.rtviwe.tport.search.SearchListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.filter_bottom_sheet.*
import kotlinx.android.synthetic.main.search_trips_fragment.*
import kotlinx.android.synthetic.main.search_trips_toolbar_filter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchTripsFragment : BaseFragment() {

    override fun getLayout() = R.layout.search_trips_fragment

    private val searchRoutesViewModel by viewModel<SearchTripsViewModel>()
    private var searchRouteCardsAdapter = GroupAdapter<ViewHolder>()

    private lateinit var searchTripClickedListener: SearchTripClickedListener
    private lateinit var searchListener: SearchListener
    private lateinit var searchRoutesLayoutManager: LinearLayoutManager
    private lateinit var searchRoutesRecyclerView: RecyclerView
    private lateinit var filterBottomSheet: BottomSheetBehavior<LinearLayout>

    private var fromPlace = ""
    private var toPlace = ""
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
            it.getString(ARGUMENT_FROM_PLACE)?.let { place -> fromPlace = place }
            it.getString(ARGUMENT_TO_PLACE)?.let { place -> toPlace = place }
            it.getString(ARGUMENT_TRAVEL_TIME)?.let { time -> travelTime = time }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_search_routes_toolbar_label.text = getString(R.string.search_routes_toolbar_title)

        edit_text_toolbar_from_place.setText(fromPlace)
        edit_text_toolbar_to_place.setText(toPlace)
        edit_text_toolbar_when.setText(travelTime)

        searchRoutesLayoutManager = LinearLayoutManager(context)

        val tripsRequest = TripsRequest(fromPlace, toPlace, travelTime)
        searchRoutesViewModel.observeAdapter(this, searchRouteCardsAdapter, tripsRequest)

        searchRoutesRecyclerView = recycler_view_search_routes.apply {
            adapter = searchRouteCardsAdapter
            layoutManager = searchRoutesLayoutManager
        }

        filterBottomSheet = BottomSheetBehavior.from(filter_bottom_sheet)
        filterBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN

        image_button_change.setOnClickListener {
            swapDestinations()
        }

        button_filter.setOnClickListener {
            openFilterSheet()
        }
    }

    private fun swapDestinations() {
        val temp = edit_text_toolbar_from_place.text
        edit_text_toolbar_from_place.text = edit_text_toolbar_to_place.text
        edit_text_toolbar_to_place.text = temp
    }

    private fun openFilterSheet() {
        filterBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
    }

    companion object {

        const val ARGUMENT_FROM_PLACE = "from place argument"
        const val ARGUMENT_TO_PLACE = "to place argument"
        const val ARGUMENT_TRAVEL_TIME = "travel time argument"
    }
}