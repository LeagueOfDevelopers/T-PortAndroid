package com.lod.rtviwe.tport.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.search.wrappers.LogoItem
import kotlinx.android.extensions.LayoutContainer

class ViewHolderLogoItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(logoItem: LogoItem) {
    }
}