package com.lod.rtviwe.tport.ui.fragment

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.viewmodel.OrdersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrdersFragment : BaseFragment() {

    companion object {

        fun newInstance(): OrdersFragment {
            return OrdersFragment()
        }
    }

    private val ordersViewModel: OrdersViewModel by viewModel()

    override fun getLayout() = R.layout.orders_fragment

    override fun scrollToTop() {

    }
}