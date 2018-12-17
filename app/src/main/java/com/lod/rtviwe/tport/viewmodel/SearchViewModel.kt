package com.lod.rtviwe.tport.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.data.MockPopularTrips
import com.lod.rtviwe.tport.model.searchfragment.LogoItem
import com.lod.rtviwe.tport.model.searchfragment.SearchBoxItem
import com.lod.rtviwe.tport.model.searchfragment.SearchItemType
import com.lod.rtviwe.tport.model.searchfragment.TitleItem
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