package com.lod.rtviwe.tport.model.searchfragment

data class TitleItem(
    var title: String
) : SearchScrollItem(SearchItemType.TITLE_ITEM)