package com.lod.rtviwe.tport.search.items

import com.bumptech.glide.Glide
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.popular_trip_item.*

class PopularTrip(private val trip: Trip) : Item() {

    override fun getLayout() = R.layout.popular_trip_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        with(trip.destination) {
            viewHolder.text_view_popular_item_place_from.text = fromPlace.name
            viewHolder.text_view_popular_item_place_to.text = toPlace.name
        }

        Glide.with(viewHolder.containerView.context)
            .load("https://i1.photocentra.ru/images/main78/781689_main.jpg")
            .into(viewHolder.image_view_popular_item_background)

        viewHolder.card_view_popular_item.setOnClickListener {

        }
    }
}