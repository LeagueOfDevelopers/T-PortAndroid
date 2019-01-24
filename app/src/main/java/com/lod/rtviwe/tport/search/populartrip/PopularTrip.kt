package com.lod.rtviwe.tport.search.populartrip

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsFragment
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.popular_trip_item.*
import java.util.*

class PopularTrip(private val trip: Trip) : Item() {

    private lateinit var navController: NavController

    override fun getLayout() = R.layout.popular_trip_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        navController =
                Navigation.findNavController(viewHolder.containerView.context as Activity, R.id.nav_host_fragment)

        with(trip.destination) {
            viewHolder.text_view_popular_item_place_from.text = fromPlace.name
            viewHolder.text_view_popular_item_place_to.text = toPlace.name
        }

        Glide.with(viewHolder.containerView.context as Activity)
            .load("https://i1.photocentra.ru/images/main78/781689_main.jpg")
            .into(viewHolder.image_view_popular_item_background)

        viewHolder.card_view_popular_item.setOnClickListener {
            with(trip.destination) {
                val date = with(Calendar.getInstance()) {
                    "${get(Calendar.YEAR)}.${get(Calendar.MONTH + 1)}.${get(Calendar.DAY_OF_MONTH)}"
                }
                val bundle = Bundle().apply {
                    putString(SearchTripsFragment.ARGUMENT_FROM_PLACE, fromPlace.name)
                    putString(SearchTripsFragment.ARGUMENT_TO_PLACE, toPlace.name)
                    putString(SearchTripsFragment.ARGUMENT_TRAVEL_TIME, date)
                }
                navController.navigate(R.id.action_searchFragment_to_searchTripsFragment, bundle)
            }
        }
    }
}