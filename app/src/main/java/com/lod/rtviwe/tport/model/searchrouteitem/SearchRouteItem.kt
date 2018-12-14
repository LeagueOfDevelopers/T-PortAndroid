package com.lod.rtviwe.tport.model.searchrouteitem

data class SearchRouteItem(
    var time: String,
    var cost: Int,
    var chips: List<RouteItemChip>
)