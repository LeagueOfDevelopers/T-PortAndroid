package com.lod.rtviwe.tport.model.recyclerview

data class LogoItem(
    var logoUrl: String = "tport_logo.png"
) : MainScrollItem(MainScrollItemType.LOGO_ITEM)