package com.lod.rtviwe.tport.orders.items

import androidx.core.widget.TextViewCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.orders.OrdersViewModel
import com.lod.rtviwe.tport.search.searchtrips.items.RouteItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.coming_order_item.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.math.roundToInt

class ComingOrderCardItem(private val trip: Trip) : Item(), KoinComponent {

    private val ordersViewModel by inject<OrdersViewModel>()

    override fun getLayout() = R.layout.coming_order_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.text_view_travel_time.text = if (trip.isPaid()) {
            TextViewCompat.setTextAppearance(viewHolder.text_view_travel_time, R.style.TextViewIsPaid)
            viewHolder.containerView.context.getString(R.string.is_paid)
        } else {
            TextViewCompat.setTextAppearance(viewHolder.text_view_travel_time, R.style.TextViewIsNotPaid)
            viewHolder.containerView.context.getString(R.string.not_paid)
        }

        with(trip.destination) {
            viewHolder.text_view_from_place.text = fromPlace.name
            viewHolder.text_view_to_place.text = toPlace.name
        }

        viewHolder.text_view_cost.text =
            String.format(viewHolder.containerView.context.getString(R.string.money), trip.cost.roundToInt())

        val searchRouteItemAdapter = GroupAdapter<ViewHolder>()
        val searchRoutesLayoutManager =
            LinearLayoutManager(viewHolder.containerView.context, RecyclerView.HORIZONTAL, false)

        trip.routes.forEachIndexed { index, route ->
            val isFirst = index == 0
            val isLast = index == trip.routes.size - 1
            searchRouteItemAdapter.add(Section(RouteItem(route, isFirst, isLast)))
        }

        viewHolder.recycler_view_routes.apply {
            adapter = searchRouteItemAdapter
            layoutManager = searchRoutesLayoutManager
        }

        viewHolder.card_current_order_Item.setOnClickListener {
            ordersViewModel.navigateToTripDetails(it.findNavController(), trip)
        }
    }
}