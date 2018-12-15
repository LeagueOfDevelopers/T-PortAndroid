package com.lod.rtviwe.tport.model.searchfragment

data class SearchItem(
    var fromPlace: String,
    var toPlace: String,
    var travelTime: String
) : SearchScrollItem(SearchItemType.SEARCH_ITEM)