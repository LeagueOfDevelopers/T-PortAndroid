package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.searchfragment.PopularItem
import com.lod.rtviwe.tport.model.searchfragment.SearchScrollItem

object MockMainScroll : MainScrollItemsProvider {

    override fun getItems(): MutableLiveData<MutableList<SearchScrollItem>> = data

    var data = MutableLiveData<MutableList<SearchScrollItem>>().apply {
        postValue(
            mutableListOf(
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "Piter",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                )
            )
        )
    }
}