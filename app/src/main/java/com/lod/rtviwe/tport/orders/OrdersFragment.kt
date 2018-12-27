package com.lod.rtviwe.tport.orders

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.orders_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OrdersFragment : BaseFragment() {

    companion object {

        fun newInstance(): OrdersFragment {
            return OrdersFragment()
        }
    }

    private val ordersViewModel: OrdersViewModel by sharedViewModel()

    private lateinit var ordersAdapter: GroupAdapter<ViewHolder>
    private lateinit var ordersLayoutManager: LinearLayoutManager
    private lateinit var ordersRecyclerView: RecyclerView

    override fun getLayout() = R.layout.orders_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersAdapter = GroupAdapter()
        ordersLayoutManager = LinearLayoutManager(context)

        ordersViewModel.observeAdapter(this, ordersAdapter)

        ordersRecyclerView = recycler_view_orders.apply {
            layoutManager = ordersLayoutManager
            adapter = ordersAdapter
        }
    }
}