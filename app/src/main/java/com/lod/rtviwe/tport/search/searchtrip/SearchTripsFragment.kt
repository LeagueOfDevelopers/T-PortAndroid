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
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_trips_fragment.*
import kotlinx.android.synthetic.main.search_trips_toolbar_filter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchTripsFragment : BaseFragment() {

    private val searchRoutesViewModel by viewModel<SearchTripsViewModel>()
    private var searchRouteCardsAdapter = GroupAdapter<ViewHolder>()

    private lateinit var searchRoutesLayoutManager: LinearLayoutManager
    private lateinit var searchRoutesRecyclerView: RecyclerView
    private lateinit var filterBottomSheetDialog: FilterBottomSheetDialog

    private var fromPlace = ""
    private var toPlace = ""
    private var travelTime = ""

    override fun getLayout() = R.layout.search_trips_fragment

    override fun scrollToTop() {
        searchRoutesLayoutManager.smoothScrollToPosition(searchRoutesRecyclerView, RecyclerView.State(), 0)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        filterBottomSheetDialog = FilterBottomSheetDialog()
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

        text_view_search_routes.text = getString(R.string.search_routes_toolbar_title)

        edit_text_from_place.setText(fromPlace)
        edit_text_to_place.setText(toPlace)
        edit_text_travel_time.setText(travelTime)

        searchRoutesLayoutManager = LinearLayoutManager(context)

        val tripsRequest = SearchTripsRequest(fromPlace, toPlace, travelTime)
        searchRoutesViewModel.observeAdapter(searchRouteCardsAdapter, tripsRequest)

        searchRoutesRecyclerView = recycler_view_search_routes.apply {
            adapter = searchRouteCardsAdapter
            layoutManager = searchRoutesLayoutManager
        }

        image_button_change.setOnClickListener {
            swapDestinations()
        }

        button_filter.setOnClickListener {
            openFilterSheet()
        }
    }

    private fun swapDestinations() {
        val temp = edit_text_from_place.text
        edit_text_from_place.text = edit_text_to_place.text
        edit_text_to_place.text = temp
    }

    private fun openFilterSheet() {
        filterBottomSheetDialog.show(activity?.supportFragmentManager, FilterBottomSheetDialog.TAG)
    }

    companion object {

        const val ARGUMENT_FROM_PLACE = "from place argument"
        const val ARGUMENT_TO_PLACE = "to place argument"
        const val ARGUMENT_TRAVEL_TIME = "travel time argument"
    }
}