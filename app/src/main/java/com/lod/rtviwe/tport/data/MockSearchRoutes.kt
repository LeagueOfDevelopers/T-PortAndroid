package com.lod.rtviwe.tport.data

import androidx.lifecycle.MutableLiveData
import com.lod.rtviwe.tport.model.searchrouteitem.RouteItemChip
import com.lod.rtviwe.tport.model.searchrouteitem.RouteType
import com.lod.rtviwe.tport.model.searchrouteitem.SearchRouteItem

object MockSearchRoutes : SearchRoutesItemsProvider {

    override fun getItems() = items

    private val items = MutableLiveData<MutableList<SearchRouteItem>>().apply {
        postValue(
            mutableListOf(
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                ),
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                ),
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                ),
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                ),
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                ),
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                ),
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                ),
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                ),
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                ),
                SearchRouteItem(
                    "9 ч 41 мин", 420, listOf(
                        RouteItemChip("Uber", RouteType.TAXI, 420),
                        RouteItemChip("Aeroflot", RouteType.AIRPLANE, 2880),
                        RouteItemChip("Aeroexpress", RouteType.TRAIN, 500)
                    )
                )
            )
        )
    }
}