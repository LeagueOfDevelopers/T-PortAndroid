package com.lod.rtviwe.tport.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.searchfragment.*
import com.lod.rtviwe.tport.model.searchfragment.SearchItemType.*
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderLogoItem
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderPopularItem
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderSearchItem
import com.lod.rtviwe.tport.ui.viewholder.ViewHolderTitleItem

class SearchAdapter(
    private val context: Context?,
    private var searchItemsList: List<SearchItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var searchViewHolder: ViewHolderSearchItem? = null

    override fun getItemCount() = searchItemsList.size

    override fun getItemViewType(position: Int): Int = searchItemsList[position].type.ordinal

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
        else -> throw RuntimeException("Unknown boxItem type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            SEARCH_ITEM.ordinal -> {
                searchViewHolder = holder as ViewHolderSearchItem
                searchViewHolder?.bind(searchItemsList[position] as SearchBoxItem)
            }
            POPULAR_ITEM.ordinal -> (holder as ViewHolderPopularItem).bind(searchItemsList[position] as PopularItem)
            LOGO_ITEM.ordinal -> (holder as ViewHolderLogoItem).bind(searchItemsList[position] as LogoItem)
            TITLE_ITEM.ordinal -> (holder as ViewHolderTitleItem).bind(searchItemsList[position] as TitleItem)
        }
    }

    fun setData(newData: List<SearchItem>) {
        searchItemsList = newData
        notifyDataSetChanged()
    }
}