package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.recyclerview.MainScrollItem
import com.lod.rtviwe.tport.model.recyclerview.PopularItem

object MockMainScroll : MainScrollItemsProvider {

    override fun getItems(): MutableLiveData<MutableList<MainScrollItem>> = data

    var data = MutableLiveData<MutableList<MainScrollItem>>().apply {
        postValue(
            mutableListOf(
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg"),
                PopularItem("Moscow", "https://i1.photocentra.ru/images/main78/781689_main.jpg")
            )
        )
    }
}