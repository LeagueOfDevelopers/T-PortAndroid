package com.lod.rtviwe.tport.ui.fragment

import android.content.Context
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.listeners.SearchListener
import com.lod.rtviwe.tport.viewmodel.SearchRoutesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRoutesFragment : BaseFragment() {

    override fun getLayout() = R.layout.search_routes_fragment

    companion object {

        fun newInstance(): SearchRoutesFragment {
            return SearchRoutesFragment()
        }
    }

    private val searchRoutesViewModel by viewModel<SearchRoutesViewModel>()

    private lateinit var searchListener: SearchListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when (context) {
            is SearchListener -> searchListener = context
            else -> throw ClassCastException("$context does not implements SearchListener")
        }
    }

}