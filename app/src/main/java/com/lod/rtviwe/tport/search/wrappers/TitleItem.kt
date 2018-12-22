package com.lod.rtviwe.tport.search.wrappers

data class TitleItem(
    var title: String
) : SearchItem(SearchItemType.TITLE_ITEM)