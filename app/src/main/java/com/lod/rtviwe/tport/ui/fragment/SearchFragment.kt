package com.lod.rtviwe.tport.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.mainscrollitem.SearchItem
import com.lod.rtviwe.tport.ui.adapter.SearchAdapter
import com.lod.rtviwe.tport.viewmodel.SearchViewModel
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

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchLayoutManager: LinearLayoutManager
    private lateinit var searchRecyclerView: RecyclerView
    private val searchItem by inject<SearchItem>()

    override fun getLayout() = R.layout.search_fragment

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
}