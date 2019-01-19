package com.lod.rtviwe.tport.search.items

import com.bumptech.glide.Glide
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.SearchListener
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.popular_trip_item.*
import java.util.*

class PopularTrip(private val trip: Trip) : Item() {

    private lateinit var searchListener: SearchListener

    override fun getLayout() = R.layout.popular_trip_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        when (viewHolder.containerView.context) {
            is SearchListener -> searchListener = viewHolder.containerView.context as SearchListener
            else -> throw ClassCastException("${viewHolder.containerView.context} does not implement SearchListener")
        }

        with(trip.destination) {
            viewHolder.text_view_popular_item_place_from.text = fromPlace.name
            viewHolder.text_view_popular_item_place_to.text = toPlace.name
        }

        Glide.with(viewHolder.containerView.context)
            .load("https://i1.photocentra.ru/images/main78/781689_main.jpg")
            .into(viewHolder.image_view_popular_item_background)

        viewHolder.card_view_popular_item.setOnClickListener {
            with(trip.destination) {
                val date = with(Calendar.getInstance()) {
                    "${get(Calendar.YEAR)}.${get(Calendar.MONTH + 1)}.${get(Calendar.DAY_OF_MONTH)}"
                }
                searchListener.openSearchTrips(fromPlace.name, toPlace.name, date)
            }
        }
    }
}