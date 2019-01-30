package com.lod.rtviwe.tport.search

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.base.ScrollableFragment
import kotlinx.android.synthetic.main.fragment_search_container.*

class SearchContainerFragment : BaseFragment(), ScrollableFragment {

    override fun getLayout() = R.layout.fragment_search_container

    override fun scrollToTop() {
        scroll_view_search_fragment.smoothScrollTo(0, 0)
    }
}