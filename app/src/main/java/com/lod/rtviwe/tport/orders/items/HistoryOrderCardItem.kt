package com.lod.rtviwe.tport.orders.items

import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class HistoryOrderCardItem(private val trip: Trip) : Item() {

    override fun getLayout() = R.layout.history_order_item

    override fun bind(viewHolder: ViewHolder, position: Int) {

    }
}