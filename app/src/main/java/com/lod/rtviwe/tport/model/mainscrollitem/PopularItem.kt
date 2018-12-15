package com.lod.rtviwe.tport.model.mainscrollitem

data class PopularItem(
    var placeFrom: String,
    val placeTo: String,
    var backgroundUrl: String
) : MainScrollItem(MainScrollItemType.POPULAR_ITEM)