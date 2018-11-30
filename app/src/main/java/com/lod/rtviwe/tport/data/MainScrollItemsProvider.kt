package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.recyclerview.MainScrollItem

interface MainScrollItemsProvider {

    fun getItems(): MutableLiveData<MutableList<MainScrollItem>>
}