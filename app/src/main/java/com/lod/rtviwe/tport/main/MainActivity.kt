package com.lod.rtviwe.tport.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseActivity
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.orders.OrderTripClickedListener
import com.lod.rtviwe.tport.profile.registration.*
import com.lod.rtviwe.tport.search.SearchListener
import com.lod.rtviwe.tport.search.searchtrip.SearchTripClickedListener
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsFragment
import com.lod.rtviwe.tport.tripdetails.TripDetailsFragment
import com.lod.rtviwe.tport.utils.AuthService
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), RegisterStepOneListener, RegisterStepTwoListener, RegisterStepThreeListener,
    SearchListener, SearchTripClickedListener, OrderTripClickedListener {

    private val authService by inject<AuthService>()
    private lateinit var navController: NavController

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_navigation, navController)

        bottom_navigation.setOnNavigationItemSelectedListener {
            navController.navigate(getCurrentFragmentId(it.itemId))
            true
        }

        bottom_navigation.setOnNavigationItemReselectedListener {
            scrollFragmentToTop()
        }

        bottom_navigation.itemIconTintList = null
    }

    override fun onRegisterStepOneContinue(phoneNumber: String) {
        val bundle = Bundle().apply { putString(RegisterStepOneFragment.ARGUMENT_PHONE_NUMBER, phoneNumber) }
        navController.navigate(R.id.action_registerStepOneFragment_to_registerStepTwoFragment, bundle)
    }

    override fun onRegisterStepTwoContinue(phoneNumber: String) {
        val bundle = Bundle().apply { putString(RegisterStepTwoFragment.ARGUMENT_PHONE_NUMBER, phoneNumber) }
        navController.navigate(R.id.action_registerStepTwoFragment_to_registerStepThreeFragment)
    }

    override fun onRegisterStepThreeContinue() {
        navController.navigate(R.id.action_registerStepThreeFragment_to_profileFragment)
    }

    override fun openTripDetailsFragmentFromSearch(trip: Trip) {
        val bundle = Bundle().apply { putParcelable(TripDetailsFragment.ARGUMENT_TRIP, trip) }
        navController.navigate(R.id.action_searchTripsFragment_to_tripDetailsFragment, bundle)
    }

    override fun openTripDetailFragmentFromOrder(trip: Trip) {
        val bundle = Bundle().apply { putParcelable(TripDetailsFragment.ARGUMENT_TRIP, trip) }
        navController.navigate(R.id.action_ordersFragment_to_tripDetailsFragment, bundle)
    }

    override fun onPickUpButton(fromPlace: String, toPlace: String, travelTime: String) {
        val bundle = Bundle().apply {
            putString(SearchTripsFragment.ARGUMENT_FROM_PLACE, fromPlace)
            putString(SearchTripsFragment.ARGUMENT_TO_PLACE, toPlace)
            putString(SearchTripsFragment.ARGUMENT_TRAVEL_TIME, travelTime)
        }
        navController.navigate(R.id.action_searchFragment_to_searchTripsFragment, bundle)
    }

    private fun getCurrentFragmentId(actionId: Int) = when (actionId) {
        R.id.action_search -> R.id.searchFragment
        R.id.action_orders -> R.id.ordersFragment
        R.id.action_bonuses -> R.id.bonusesFragment
        R.id.action_profile -> if (isUserLogged()) R.id.profileFragment else R.id.registerStepOneFragment
        else -> -1
    }

    private fun scrollFragmentToTop() {
        // TODO
    }

    // TODO replace it back
    private fun isUserLogged() = false /*authService.getToken(baseContext) != null*/
}
