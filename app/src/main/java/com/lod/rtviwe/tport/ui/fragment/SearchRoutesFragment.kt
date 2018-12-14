package com.lod.rtviwe.tport.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.adapter.SearchRoutesAdapter
import com.lod.rtviwe.tport.ui.listeners.SearchListener
import com.lod.rtviwe.tport.viewmodel.SearchRoutesViewModel
import kotlinx.android.synthetic.main.search_routes_fragment.*
import kotlinx.android.synthetic.main.search_routes_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRoutesFragment : BaseFragment() {

    override fun getLayout() = R.layout.search_routes_fragment

    companion object {

        fun newInstance(fromPlace: String, toPlace: String): SearchRoutesFragment {
            val newArguments = Bundle().apply {
                putString(STATE_FROM_PLACE, fromPlace)
                putString(STATE_TO_PLACE, toPlace)
            }
            return SearchRoutesFragment().apply {
                arguments = newArguments
            }
        }

        private const val STATE_FROM_PLACE = "FROM_PLACE_STATE"
        private const val STATE_TO_PLACE = "FROM_TO_STATE"
    }

    private val searchRoutesViewModel by viewModel<SearchRoutesViewModel>()

    private lateinit var searchListener: SearchListener
    private lateinit var searchRoutesAdapter: SearchRoutesAdapter
    private lateinit var searchRoutesLayoutManager: LinearLayoutManager
    private lateinit var searchRoutesRecyclerView: RecyclerView

    private var fromPlace = ""
    private var toPlace = ""

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is SearchListener -> searchListener = context
            else -> throw ClassCastException("$context does not implements SearchListener")
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            if (it.containsKey(STATE_FROM_PLACE)) {
                fromPlace = it.getString(STATE_FROM_PLACE)
            }
            if (it.containsKey(STATE_TO_PLACE)) {
                toPlace = it.getString(STATE_TO_PLACE)
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit_text_toolbar_from_place.setText(fromPlace)
        edit_text_toolbar_to_place.setText(toPlace)

        searchRoutesAdapter = SearchRoutesAdapter(context, listOf())
        searchRoutesLayoutManager = LinearLayoutManager(context)

        searchRoutesViewModel.observeAdapter(this, searchRoutesAdapter)

        searchRoutesRecyclerView = recycler_view_search_routes.apply {
            adapter = searchRoutesAdapter
            layoutManager = searchRoutesLayoutManager

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        fab_search_routes.visibility = View.INVISIBLE
                        // fab_search_routes.animate().yBy(-200f).setDuration(200).start()
                    } else if (dy < 0) {
                        fab_search_routes.visibility = View.VISIBLE
                        // fab_search_routes.animate().yBy(200f).setDuration(200).start()
                    }
                }
            })
        }
    }
}