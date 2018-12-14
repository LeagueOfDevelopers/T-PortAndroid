package com.lod.rtviwe.tport.ui.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
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
        text_view_search_route_cost.text = "${item.cost} \u20BD" // \u20BD - знак рубля

        chip_group_small_routes.removeAllViews()
        chip_group_big_routes.removeAllViews()
        chip_group_small_routes.chipSpacingHorizontal = 0
        chip_group_small_routes.chipSpacingVertical = 32

        item.chips.forEach {
            val smallChip = Chip(containerView.context).apply {
                text = it.text
                chipCornerRadius = 8f
                chipMinHeight = 80f
                textStartPadding = 0f
                textEndPadding = 0f
                setTextAppearance(R.style.ChipSmallText)

                when (it.type) {
                    RouteType.TAXI -> {
                        chipBackgroundColor =
                                containerView.context.getColorStateList(R.color.colorSmallChipTaxiBackground)
                        chipIcon = containerView.context.getDrawable(R.drawable.ic_local_taxi_white_24dp)
                    }
                    RouteType.AIRPLANE -> {
                        chipBackgroundColor =
                                containerView.context.getColorStateList(R.color.colorSmallChipAirplaneBackground)
                        chipIcon = containerView.context.getDrawable(R.drawable.ic_airplanemode_active_white_24dp)
                    }
                    RouteType.TRAIN -> {
                        chipBackgroundColor =
                                containerView.context.getColorStateList(R.color.colorSmallChipTrainBackground)
                        chipIcon = containerView.context.getDrawable(R.drawable.ic_directions_railway_white_24dp)
                    }
                    RouteType.SHIP -> {
                        chipBackgroundColor =
                                containerView.context.getColorStateList(R.color.colorSmallChipShipBackground)
                        chipIcon = containerView.context.getDrawable(R.drawable.ic_directions_boat_white_24dp)
                    }
                    RouteType.FOOT -> {
                        chipBackgroundColor =
                                containerView.context.getColorStateList(R.color.colorSmallChipFootBackground)
                        chipIcon = containerView.context.getDrawable(R.drawable.ic_directions_walk_white_24dp)
                    }
                }
            }

            chip_group_small_routes.addView(smallChip)
            chip_group_small_routes.addView(getArrowImageView())

            val bigChip = Chip(containerView.context).apply {
                text = it.text
                chipCornerRadius = 2f
                chipMinHeight = 64f
                isClickable = false
                setTextAppearance(R.style.ChipBigText)
                chipBackgroundColor =
                        containerView.context.getColorStateList(R.color.colorTransparent)
            }

            chip_group_big_routes.addView(bigChip)
            chip_group_big_routes.addView(getArrowImageView())
        }

        chip_group_small_routes.removeViewAt(chip_group_small_routes.size - 1)
        chip_group_big_routes.removeViewAt(chip_group_big_routes.size - 1)
    }

    @SuppressLint("PrivateResource")
    private fun getArrowImageView() = ImageView(containerView.context).apply {
        setImageResource(R.drawable.abc_ic_arrow_drop_right_black_24dp)
    }
}