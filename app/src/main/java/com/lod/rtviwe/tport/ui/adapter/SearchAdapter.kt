package com.lod.rtviwe.tport.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.recyclerview.LogoItem
import com.lod.rtviwe.tport.model.recyclerview.MainScrollItem
import com.lod.rtviwe.tport.model.recyclerview.MainScrollItemType.LOGO_ITEM
import com.lod.rtviwe.tport.model.recyclerview.MainScrollItemType.POPULAR_ITEM
import com.lod.rtviwe.tport.model.recyclerview.MainScrollItemType.SEARCH_ITEM
import com.lod.rtviwe.tport.model.recyclerview.MainScrollItemType.TITLE_ITEM
import com.lod.rtviwe.tport.model.recyclerview.PopularItem
import com.lod.rtviwe.tport.model.recyclerview.SearchItem
import com.lod.rtviwe.tport.model.recyclerview.TitleItem
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderLogoItem
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderPopularItem
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderSearchItem
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderTitleItem

class SearchAdapter(
    private val context: Context?,
    private var data: List<MainScrollItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var searchViewHolder: ViewHolderSearchItem? = null

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int = data[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        SEARCH_ITEM.ordinal -> ViewHolderSearchItem(
            LayoutInflater.from(context)
                .inflate(R.layout.search_item, parent, false)
        )
        POPULAR_ITEM.ordinal -> ViewHolderPopularItem(
            LayoutInflater.from(context)
                .inflate(R.layout.popular_item, parent, false)
        )
        LOGO_ITEM.ordinal -> ViewHolderLogoItem(
            LayoutInflater.from(context)
                .inflate(R.layout.logo_item, parent, false)
        )
        TITLE_ITEM.ordinal -> ViewHolderTitleItem(
            LayoutInflater.from(context)
                .inflate(R.layout.title_item, parent, false)
        )
        else -> throw RuntimeException("Unknown item type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            SEARCH_ITEM.ordinal -> {
                searchViewHolder = holder as ViewHolderSearchItem
                searchViewHolder?.bind(data[position] as SearchItem)
            }
            POPULAR_ITEM.ordinal -> (holder as ViewHolderPopularItem).bind(data[position] as PopularItem)
            LOGO_ITEM.ordinal -> (holder as ViewHolderLogoItem).bind(data[position] as LogoItem)
            TITLE_ITEM.ordinal -> (holder as ViewHolderTitleItem).bind(data[position] as TitleItem)
        }
    }

    fun setData(newData: List<MainScrollItem>) {
        data = newData
        notifyDataSetChanged()
    }
}