package com.lod.rtviwe.tport.model.mainscrollitem

data class LogoItem(
    var logoUrl: String = "tport_logo.png"
) : MainScrollItem(MainScrollItemType.LOGO_ITEM)