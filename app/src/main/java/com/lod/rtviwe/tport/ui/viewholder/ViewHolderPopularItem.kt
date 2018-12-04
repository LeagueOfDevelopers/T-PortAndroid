package com.lod.rtviwe.tport.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lod.rtviwe.tport.model.recyclerview.PopularItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.popular_item.*

class ViewHolderPopularItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(popularItem: PopularItem) {
        text_view_popular_item_name.text = popularItem.name

        Glide.with(containerView.context)
            .load(popularItem.backgroundUrl)
            .into(image_view_popular_item_background)

        card_view_popular_item.setOnClickListener {

        }
    }
}