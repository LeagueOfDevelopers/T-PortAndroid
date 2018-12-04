package com.lod.rtviwe.tport.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.data.MockMainScroll
import com.lod.rtviwe.tport.model.recyclerview.LogoItem
import com.lod.rtviwe.tport.model.recyclerview.MainScrollItemType
import com.lod.rtviwe.tport.model.recyclerview.SearchItem
import com.lod.rtviwe.tport.model.recyclerview.TitleItem
import com.lod.rtviwe.tport.ui.adapter.SearchAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SearchViewModel(private val app: Application) : BaseViewModel(app) {

    private val job = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun observeAdapter(owner: LifecycleOwner, searchItem: SearchItem, searchAdapter: SearchAdapter) {
        MockMainScroll.getItems().observe(owner, Observer {
            if (it[0].type != MainScrollItemType.LOGO_ITEM) {
                it.addAll(
                    0,
                    listOf(
                        LogoItem(),
                        searchItem,
                        TitleItem(app.getString(R.string.title_item))
                    )
                )
            }

            searchAdapter.setData(it)
        })
    }
}