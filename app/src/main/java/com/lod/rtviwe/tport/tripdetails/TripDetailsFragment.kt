package com.lod.rtviwe.tport.tripdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseFragment
import com.lod.rtviwe.tport.model.FullTrip
import kotlinx.android.synthetic.main.search_routes_toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class TripDetailsFragment : BaseFragment() {

    companion object {

        fun newInstance(fullTrip: FullTrip): TripDetailsFragment {
            val newArguments = Bundle().apply {
                putParcelable(STATE_TRIP, fullTrip)
            }

            return TripDetailsFragment().apply {
                arguments = newArguments
            }
        }

        private const val STATE_TRIP = "TRIP_STATE"
    }

    private lateinit var fullTrip: FullTrip

    override fun getLayout() = R.layout.trip_details_fragment

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            fullTrip = it.getParcelable(STATE_TRIP)
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_search_routes_toolbar_label.text = getString(R.string.trip_details_toolbar_title)

        edit_text_toolbar_from_place.setText(fullTrip.trip.placeFrom.name)
        edit_text_toolbar_to_place.setText(fullTrip.trip.placeTo.name)
        edit_text_toolbar_when.setText(
            SimpleDateFormat(
                "dd.MM.yyyy",
                Locale.getDefault()
            ).format(fullTrip.trip.timeTravel)
        )
    }
}