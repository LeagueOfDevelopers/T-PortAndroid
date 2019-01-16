package com.lod.rtviwe.tport.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseActivity
import com.lod.rtviwe.tport.model.Trip
import com.lod.rtviwe.tport.orders.OrderTripClickedListener
import com.lod.rtviwe.tport.profile.registration.RegisterStepOneListener
import com.lod.rtviwe.tport.profile.registration.RegisterStepThreeListener
import com.lod.rtviwe.tport.profile.registration.RegisterStepTwoListener
import com.lod.rtviwe.tport.search.SearchListener
import com.lod.rtviwe.tport.search.searchtrip.SearchTripClickedListener
import com.lod.rtviwe.tport.utils.AuthService
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), RegisterStepOneListener, RegisterStepTwoListener, RegisterStepThreeListener,
    SearchListener, SearchTripClickedListener, OrderTripClickedListener {

    private var registerPhoneNumber = ""
    private var registerCode = ""
    private var fromPlaceSearchBox = ""
    private var toPlaceSearchBox = ""
    private var travelTimeSearchBox = ""
    private var tripInSearchFragment: Trip? = null
    private var tripInOrdersFragment: Trip? = null
    private val authService by inject<AuthService>()

    private lateinit var navController: NavController

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_navigation, navController)

        bottom_navigation.setOnNavigationItemReselectedListener {
            scrollFragmentToTop()
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            navController.navigate(getCurrentFragmentId(it.itemId))
            true
        }
    }

    private fun getCurrentFragmentId(actionId: Int) = when (actionId) {
        R.id.action_search -> R.id.searchFragment
        R.id.action_orders -> R.id.ordersFragment
        R.id.action_bonuses -> R.id.bonusesFragment
        R.id.action_profile -> if (isUserLogged()) R.id.profileFragment else R.id.registerStepOneFragment
        else -> -1
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.apply {
            putString(STATE_REGISTER_PHONE_NUMBER, registerPhoneNumber)
            putString(STATE_FROM_PLACE, fromPlaceSearchBox)
            putString(STATE_TO_PLACE, toPlaceSearchBox)
            putString(STATE_TRAVEL_TIME, travelTimeSearchBox)
            putString(STATE_CODE, registerCode)
        }

        super.onSaveInstanceState(outState)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            registerPhoneNumber = savedInstanceState.getString(STATE_REGISTER_PHONE_NUMBER)
            fromPlaceSearchBox = savedInstanceState.getString(STATE_FROM_PLACE)
            toPlaceSearchBox = savedInstanceState.getString(STATE_TO_PLACE)
            travelTimeSearchBox = savedInstanceState.getString(STATE_TRAVEL_TIME)
            registerCode = savedInstanceState.getString(STATE_CODE)
        }

        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onRegisterStepOneContinue(phoneNumber: String) {
        registerPhoneNumber = phoneNumber
        navController.navigate(R.id.action_registerStepOneFragment_to_registerStepTwoFragment)
    }

    override fun onRegisterStepTwoContinue() {
        navController.navigate(R.id.action_registerStepTwoFragment_to_registerStepThreeFragment)
    }

    override fun onRegisterStepThreeContinue() {
        navController.navigate(R.id.action_registerStepThreeFragment_to_profileFragment)
    }

    override fun openTripDetailsFragmentFromSearch(trip: Trip) {
        tripInSearchFragment = trip
        navController.navigate(R.id.action_searchTripsFragment_to_tripDetailsFragment)
    }

    override fun openTripDetailFragmentFromOrder(trip: Trip) {
        tripInOrdersFragment = trip
        navController.navigate(R.id.action_ordersFragment_to_tripDetailsFragment)
    }

    override fun onPickUpButton(fromPlace: String, toPlace: String, travelTime: String) {
        fromPlaceSearchBox = fromPlace
        toPlaceSearchBox = toPlace
        travelTimeSearchBox = travelTime
        navController.navigate(R.id.action_searchFragment_to_searchTripsFragment)
    }

    // TODO
    private fun scrollFragmentToTop() {

    }

    private fun isUserLogged() = authService.getToken(baseContext) != null

    companion object {

        private const val STATE_REGISTER_PHONE_NUMBER = "REGISTER_PHONE_NUMBER_STATE"
        private const val STATE_FROM_PLACE = "FROM_PLACE_STATE"
        private const val STATE_TO_PLACE = "TO_PLACE_STATE"
        private const val STATE_TRAVEL_TIME = "TRAVEL_TIME_STATE"
        private const val STATE_CODE = "CODE_STATE"
    }
}
