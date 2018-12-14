package com.lod.rtviwe.tport.model.mainscrollitem

data class PopularItem(
    var name: String,
    var backgroundUrl: String
) : MainScrollItem(MainScrollItemType.POPULAR_ITEM)