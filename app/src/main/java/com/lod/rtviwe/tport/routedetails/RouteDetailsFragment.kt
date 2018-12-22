package com.lod.rtviwe.tport.routedetails

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment

class RouteDetailsFragment : BaseFragment() {

    companion object {

        fun newInstance(): RouteDetailsFragment {
            return RouteDetailsFragment()
        }
    }

    override fun getLayout() = R.layout.route_details_fragment
}