package com.lod.rtviwe.tport.search.populartrip

import android.app.Activity
import android.graphics.Typeface
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.SearchBox
import com.lod.rtviwe.tport.search.SearchViewModel
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.popular_trip_item.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class PopularTrip(private val trip: Trip) : Item(), KoinComponent {

    private val searchViewModel by inject<SearchViewModel>()

    override fun getLayout() = R.layout.popular_trip_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        with(trip.destination) {
            viewHolder.text_view_popular_item_place_from.text = fromPlace.name
            viewHolder.text_view_popular_item_place_to.text = toPlace.name
        }

        viewHolder.text_view_popular_item_place_from.setTypeface(
            viewHolder.text_view_popular_item_place_from.typeface,
            Typeface.BOLD
        )

        viewHolder.text_view_minus.setTypeface(
            viewHolder.text_view_popular_item_place_from.typeface,
            Typeface.BOLD
        )

        viewHolder.text_view_popular_item_place_to.setTypeface(
            viewHolder.text_view_popular_item_place_from.typeface,
            Typeface.BOLD
        )

        Glide.with(viewHolder.containerView.context as Activity)
            .load("https://i1.photocentra.ru/images/main78/781689_main.jpg")
            .into(viewHolder.image_view_popular_item_background)

        viewHolder.card_view_popular_item.setOnClickListener {
            with(trip.destination) {
                val date = with(Calendar.getInstance()) {
                    "${get(Calendar.YEAR)}.${get(Calendar.MONTH + 1)}.${get(Calendar.DAY_OF_MONTH)}"
                }
                searchViewModel.navigateToSearchTrip(
                    it.findNavController(), SearchBox(
                        fromPlace.name,
                        toPlace.name,
                        date
                    )
                )
            }
        }
    }
}