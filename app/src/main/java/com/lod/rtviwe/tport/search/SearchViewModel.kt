package com.lod.rtviwe.tport.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.data.MockPopularTrips
import com.lod.rtviwe.tport.search.wrappers.LogoItem
import com.lod.rtviwe.tport.search.wrappers.SearchBoxItem
import com.lod.rtviwe.tport.search.wrappers.SearchItemType
import com.lod.rtviwe.tport.search.wrappers.TitleItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SearchViewModel(private val app: Application) : AndroidViewModel(app) {

    private val job = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun observeAdapter(owner: LifecycleOwner, searchBoxItem: SearchBoxItem, searchAdapter: SearchAdapter) {
        MockPopularTrips.getItems().observe(owner, Observer {
            if (it[0].type != SearchItemType.LOGO_ITEM) {
                it.addAll(
                    0,
                    listOf(
                        LogoItem(),
                        searchBoxItem,
                        TitleItem(app.getString(R.string.title_item))
                    )
                )
            }

            searchAdapter.setData(it)
        })
    }
}