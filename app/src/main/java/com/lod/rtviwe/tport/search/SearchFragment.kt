package com.lod.rtviwe.tport.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.search.wrappers.SearchBox
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {

    companion object {

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    private val searchViewModel by viewModel<SearchViewModel>()
    private val searchBox by inject<SearchBox>()

    private var searchAdapter = GroupAdapter<ViewHolder>()
    private lateinit var searchLayoutManager: LinearLayoutManager
    private lateinit var searchRecyclerView: RecyclerView

    override fun getLayout() = R.layout.search_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.populateAdapter(this, searchAdapter, searchBox)

        searchLayoutManager = LinearLayoutManager(context)

        searchRecyclerView = recycler_view_search.apply {
            adapter = searchAdapter
            layoutManager = searchLayoutManager
        }
    }
}