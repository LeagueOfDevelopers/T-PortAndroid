package com.lod.rtviwe.tport.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.recyclerview.SearchItem
import com.lod.rtviwe.tport.ui.adapter.SearchAdapter
import com.lod.rtviwe.tport.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {

    companion object {

        private const val STATE_FROM_PLACE = "FROM_PLACE"
        private const val STATE_TO_PLACE = "TO_PLACE"
        private const val STATE_TRAVEL_TIME = "TIME_TRAVEL"

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    private val searchViewModel by viewModel<SearchViewModel>()

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchLayoutManager: LinearLayoutManager
    private lateinit var searchRecyclerView: RecyclerView
    private val searchItem by inject<SearchItem>()

    override fun getLayout() = R.layout.search_fragment

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        savedInstanceState?.let {
            if (savedInstanceState.getString(STATE_FROM_PLACE) != null)
                searchItem.fromPlace = savedInstanceState.getString(STATE_FROM_PLACE)!!.toString()
            if (savedInstanceState.getString(STATE_TO_PLACE) != null)
                searchItem.toPlace = savedInstanceState.getString(STATE_TO_PLACE)!!.toString()
            if (savedInstanceState.getString(STATE_TRAVEL_TIME) != null)
                searchItem.travelTime = savedInstanceState.getString(STATE_TRAVEL_TIME)!!.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter = SearchAdapter(context, listOf())
        searchLayoutManager = LinearLayoutManager(context)

        searchViewModel.observeAdapter(this, searchItem, searchAdapter)

        searchRecyclerView = recycler_view_search.apply {
            adapter = searchAdapter
            layoutManager = searchLayoutManager
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_FROM_PLACE, searchAdapter.searchViewHolder?.item?.fromPlace)
        outState.putString(STATE_TO_PLACE, searchAdapter.searchViewHolder?.item?.toPlace)
        outState.putString(STATE_TRAVEL_TIME, searchAdapter.searchViewHolder?.item?.travelTime)

        super.onSaveInstanceState(outState)
    }

    override fun scrollToTop() {
        searchLayoutManager.smoothScrollToPosition(searchRecyclerView, RecyclerView.State(), 0)
    }
}