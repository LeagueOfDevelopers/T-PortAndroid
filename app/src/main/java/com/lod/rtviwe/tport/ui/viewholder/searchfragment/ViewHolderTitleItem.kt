package com.lod.rtviwe.tport.ui.viewholder.searchfragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.model.searchfragment.TitleItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.title_item.*

class ViewHolderTitleItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(titleItem: TitleItem) {
        text_view_title_item.text = titleItem.title
    }
}