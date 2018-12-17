package com.lod.rtviwe.tport.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.adapter.order.OrdersAdapter
import com.lod.rtviwe.tport.viewmodel.OrdersViewModel
import kotlinx.android.synthetic.main.orders_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrdersFragment : BaseFragment() {

    companion object {

        fun newInstance(): OrdersFragment {
            return OrdersFragment()
        }
    }

    private val ordersViewModel: OrdersViewModel by viewModel()

    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var ordersLayoutManager: LinearLayoutManager
    private lateinit var ordersRecyclerView: RecyclerView

    override fun getLayout() = R.layout.orders_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersAdapter = OrdersAdapter(context, listOf())
        ordersLayoutManager = LinearLayoutManager(context)

        ordersViewModel.observeAdapter(this, ordersAdapter)

        ordersRecyclerView = recycler_view_orders.apply {
            adapter = ordersAdapter
            layoutManager = ordersLayoutManager
        }
    }
}