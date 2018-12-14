package com.lod.rtviwe.tport.ui.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.searchrouteitem.RouteType
import com.lod.rtviwe.tport.model.searchrouteitem.SearchRouteItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_route_item.*

class ViewHolderSearchRouteItem(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private lateinit var searchRouteItem: SearchRouteItem

    @SuppressLint("SetTextI18n")
    fun bind(item: SearchRouteItem) {
        searchRouteItem = item

        text_view_route_time.text = item.time
        text_view_search_route_cost.text = "${item.cost} \u20BD"

        chip_group_small_routes.removeAllViews()
        chip_group_big_routes.removeAllViews()

        item.chips.forEach {
            val smallChip = Chip(containerView.context).apply {
                text = it.text
                when (it.type) {
                    RouteType.TAXI ->
                        setChipDrawable(ChipDrawable.createFromResource(context, R.xml.search_route_small_taxi_chip))
                    RouteType.AIRPLANE ->
                        setChipDrawable(
                            ChipDrawable.createFromResource(
                                context,
                                R.xml.search_route_small_airplane_chip
                            )
                        )
                    RouteType.TRAIN ->
                        setChipDrawable(ChipDrawable.createFromResource(context, R.xml.search_route_small_train_chip))
                    RouteType.SHIP ->
                        setChipDrawable(ChipDrawable.createFromResource(context, R.xml.search_route_small_ship_chip))
                    RouteType.FOOT ->
                        setChipDrawable(ChipDrawable.createFromResource(context, R.xml.search_route_small_foot_chip))
                }
            }
            chip_group_small_routes.addView(smallChip)

            val bigChip = Chip(containerView.context).apply {
                text = it.text
                setChipDrawable(ChipDrawable.createFromResource(context, R.xml.search_route_big_chip))
            }
            chip_group_big_routes.addView(bigChip)
        }
    }
}