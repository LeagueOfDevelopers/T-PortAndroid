package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.search.wrappers.SearchItem

interface SearchFragmentItemsProvider {

    fun getItems(): MutableLiveData<MutableList<SearchItem>>
}