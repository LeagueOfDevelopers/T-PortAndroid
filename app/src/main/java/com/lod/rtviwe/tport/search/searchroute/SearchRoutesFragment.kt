package com.lod.rtviwe.tport.search.searchroute

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.listeners.SearchListener
import com.lod.rtviwe.tport.listeners.SearchTripClickedListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_routes_fragment.*
import kotlinx.android.synthetic.main.search_routes_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRoutesFragment : BaseFragment() {

    override fun getLayout() = R.layout.search_routes_fragment

    companion object {

        fun newInstance(fromPlace: String, toPlace: String, travelTime: String): SearchRoutesFragment {
            val newArguments = Bundle().apply {
                putString(STATE_FROM_PLACE, fromPlace)
                putString(STATE_TO_PLACE, toPlace)
                putString(STATE_TRAVEL_TIME, travelTime)
            }
            return SearchRoutesFragment().apply {
                arguments = newArguments
            }
        }

        private const val STATE_FROM_PLACE = "FROM_PLACE_STATE"
        private const val STATE_TO_PLACE = "FROM_TO_STATE"
        private const val STATE_TRAVEL_TIME = "TRAVEL_TIME_STATE"
    }

    private val searchRoutesViewModel by viewModel<SearchRoutesViewModel>()
    private var searchRouteCardsAdapter = GroupAdapter<ViewHolder>()

    private lateinit var searchTripClickedListener: SearchTripClickedListener
    private lateinit var searchListener: SearchListener
    private lateinit var searchRoutesLayoutManager: LinearLayoutManager
    private lateinit var searchRoutesRecyclerView: RecyclerView

    private var fromPlace = ""
    private var toPlace = ""
    private var travelTime = ""

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is SearchListener -> searchListener = context
            else -> throw ClassCastException("$context does not implements SearchListener")
        }

        when (context) {
            is SearchTripClickedListener -> searchTripClickedListener = context
            else -> throw ClassCastException("$context does not implements SearchTripClickedListener")
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            fromPlace = it.getString(STATE_FROM_PLACE)
            toPlace = it.getString(STATE_TO_PLACE)
            travelTime = it.getString(STATE_TRAVEL_TIME)
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit_text_toolbar_from_place.setText(fromPlace)
        edit_text_toolbar_to_place.setText(toPlace)
        edit_text_toolbar_when.setText(travelTime)

        searchRoutesLayoutManager = LinearLayoutManager(context)

        searchRoutesViewModel.observeAdapter(this, searchRouteCardsAdapter)

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
}