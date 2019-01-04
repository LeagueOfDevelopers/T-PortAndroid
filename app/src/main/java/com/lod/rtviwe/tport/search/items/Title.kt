package com.lod.rtviwe.tport.search.items

import com.lod.rtviwe.tport.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.title_item.*

class Title(private val title: String) : Item() {

    override fun getLayout() = R.layout.title_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.text_view_title_item.text = title
    }
}