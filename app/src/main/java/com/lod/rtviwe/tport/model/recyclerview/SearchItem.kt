package com.lod.rtviwe.tport.model.recyclerview

data class SearchItem(
    var fromPlace: String,
    var toPlace: String,
    var travelTime: String
) : MainScrollItem(MainScrollItemType.SEARCH_ITEM)