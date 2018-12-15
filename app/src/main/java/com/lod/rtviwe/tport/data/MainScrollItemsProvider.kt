package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.searchfragment.SearchScrollItem

interface MainScrollItemsProvider {

    fun getItems(): MutableLiveData<MutableList<SearchScrollItem>>
}