package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.searchfragment.SearchItem

interface SearchFragmentItemsProvider {

    fun getItems(): MutableLiveData<MutableList<SearchItem>>
}