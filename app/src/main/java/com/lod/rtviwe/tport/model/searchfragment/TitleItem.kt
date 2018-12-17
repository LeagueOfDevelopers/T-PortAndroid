package com.lod.rtviwe.tport.model.searchfragment

data class TitleItem(
    var title: String
) : SearchItem(SearchItemType.TITLE_ITEM)