package com.lod.rtviwe.tport.model.searchfragment

data class PopularItem(
    var placeFrom: String,
    val placeTo: String,
    var backgroundUrl: String
) : SearchItem(SearchItemType.POPULAR_ITEM)