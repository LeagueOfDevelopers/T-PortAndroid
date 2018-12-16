package com.lod.rtviwe.tport.model.searchrouteitem

data class SearchRouteCardItem(
    var time: String,
    var cost: Int,
    var items: List<RouteItem>
)