package com.lod.rtviwe.tport.tripdetails

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment

class TripDetailsFragment : BaseFragment() {

    companion object {

        fun newInstance(): TripDetailsFragment {
            return TripDetailsFragment()
        }
    }

    override fun getLayout() = R.layout.trip_details_fragment

}