package com.lod.rtviwe.tport.orders.items

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Place
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.order_place_item.*

class OrderDestinationFirstItem(private val place: Place) : Item() {

    override fun getLayout() = R.layout.order_place_first_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.text_view_location.text = place.name
    }
}