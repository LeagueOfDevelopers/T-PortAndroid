package com.lod.rtviwe.tport.bonuses

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.base.ScrollableFragment
import kotlinx.android.synthetic.main.fragment_bonuses_container.*

class BonusesContainerFragment : BaseFragment(), ScrollableFragment {

    override fun getLayout() = R.layout.fragment_bonuses_container

    override fun scrollToTop() {
        scroll_view_bonuses_fragment.smoothScrollTo(0, 0)
    }
}