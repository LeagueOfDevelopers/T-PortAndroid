package com.lod.rtviwe.tport.model.searchfragment

data class SearchBoxItem(
    var fromPlace: String,
    var toPlace: String,
    var travelTime: String
) : SearchItem(SearchItemType.SEARCH_ITEM)