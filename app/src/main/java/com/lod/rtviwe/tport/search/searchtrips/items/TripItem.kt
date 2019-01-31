package com.lod.rtviwe.tport.search.searchtrips.items

import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.search.SearchViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_trip_card.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class TripItem(private val trip: Trip) : Item(), KoinComponent {

    private val searchViewModel by inject<SearchViewModel>()

    override fun getLayout() = R.layout.search_trip_card

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.text_view_route_time.text =
                SimpleDateFormat("hh:mm", Locale.getDefault()).format(trip.routes[0].departureDate)
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

        viewHolder.card_search_trip_item.setOnClickListener {
            searchViewModel.navigateToTripDetails(it.findNavController(), trip)
        }
    }
}