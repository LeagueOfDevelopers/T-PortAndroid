package com.lod.rtviwe.tport.model.searchfragment

data class LogoItem(
    var logoUrl: String = "tport_logo.png"
) : SearchScrollItem(SearchItemType.LOGO_ITEM)