package com.lod.rtviwe.tport.search.wrappers

data class SearchBoxItem(
    var fromPlace: String,
    var toPlace: String,
    var travelTime: String
) : SearchItem(SearchItemType.SEARCH_ITEM)