package com.lod.rtviwe.tport.model.recyclerview

data class PopularItem(
    var name: String,
    var backgroundUrl: String
) : MainScrollItem(MainScrollItemType.POPULAR_ITEM)