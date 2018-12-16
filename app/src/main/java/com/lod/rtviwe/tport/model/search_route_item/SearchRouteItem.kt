package com.lod.rtviwe.tport.model.search_route_item

data class SearchRouteItem(
    var time: String,
    var cost: Int,
    var chips: List<RouteItem>
)