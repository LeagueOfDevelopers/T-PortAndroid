package com.lod.rtviwe.tport.model.main_scroll_item

data class PopularItem(
    var name: String,
    var backgroundUrl: String
) : MainScrollItem(MainScrollItemType.POPULAR_ITEM)