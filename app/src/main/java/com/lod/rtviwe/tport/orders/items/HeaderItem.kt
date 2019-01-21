package com.lod.rtviwe.tport.orders.items

import com.lod.rtviwe.tport.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.header_item.view.*

class HeaderItem(private val text: String) : Item() {

    override fun getLayout() = R.layout.header_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.containerView.text_view_header.text = text
    }
}