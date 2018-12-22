package com.lod.rtviwe.tport.search.wrappers

data class LogoItem(
    var logoUrl: String = "tport_logo.png"
) : SearchItem(SearchItemType.LOGO_ITEM)