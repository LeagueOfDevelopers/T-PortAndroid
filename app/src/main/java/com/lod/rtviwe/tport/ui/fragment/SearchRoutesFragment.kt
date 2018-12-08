package com.lod.rtviwe.tport.ui.fragment

import com.lod.rtviwe.tport.R
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


}