package com.lod.rtviwe.tport.profile

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.base.ScrollableFragment
import kotlinx.android.synthetic.main.fragment_profile_container.*

class ProfileContainerFragment : BaseFragment(), ScrollableFragment {

    override fun getLayout() = R.layout.fragment_profile_container

    override fun scrollToTop() {
        scroll_view_profile_fragment.smoothScrollTo(0, 0)
    }
}