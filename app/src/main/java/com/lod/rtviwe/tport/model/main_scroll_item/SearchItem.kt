package com.lod.rtviwe.tport.model.main_scroll_item

data class SearchItem(
    var fromPlace: String,
    var toPlace: String,
    var travelTime: String
) : MainScrollItem(MainScrollItemType.SEARCH_ITEM)