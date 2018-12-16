package com.lod.rtviwe.tport.model.main_scroll_item

data class LogoItem(
    var logoUrl: String = "tport_logo.png"
) : MainScrollItem(MainScrollItemType.LOGO_ITEM)