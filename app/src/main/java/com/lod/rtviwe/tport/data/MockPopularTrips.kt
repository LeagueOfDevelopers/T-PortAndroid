package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.search.wrappers.PopularItem
import com.lod.rtviwe.tport.search.wrappers.SearchItem

object MockPopularTrips : SearchFragmentItemsProvider {

    override fun getItems() = data

    var data = MutableLiveData<MutableList<SearchItem>>().apply {
        postValue(
            mutableListOf(
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                ),
                PopularItem(
                    "Moscow",
                    "St. Petersburg",
                    "https://i1.photocentra.ru/images/main78/781689_main.jpg"
                )
            )
        )
    }
}