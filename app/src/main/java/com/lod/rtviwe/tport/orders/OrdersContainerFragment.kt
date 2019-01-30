package com.lod.rtviwe.tport.orders

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.base.ScrollableFragment
import kotlinx.android.synthetic.main.fragment_orders_container.*

class OrdersContainerFragment : BaseFragment(), ScrollableFragment {

    override fun getLayout() = R.layout.fragment_orders_container

    override fun scrollToTop() {
        scroll_view_orders_fragment.smoothScrollTo(0, 0)
    }
}