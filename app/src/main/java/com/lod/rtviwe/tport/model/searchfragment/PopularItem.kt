package com.lod.rtviwe.tport.model.searchfragment

data class PopularItem(
    var placeFrom: String,
    val placeTo: String,
    var backgroundUrl: String
) : SearchScrollItem(SearchItemType.POPULAR_ITEM)