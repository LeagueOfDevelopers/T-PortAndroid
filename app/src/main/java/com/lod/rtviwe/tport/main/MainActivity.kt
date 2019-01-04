package com.lod.rtviwe.tport.main

import android.os.Bundle
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseActivity
import com.lod.rtviwe.tport.bonuses.BonusesFragment
import com.lod.rtviwe.tport.model.FullTrip
import com.lod.rtviwe.tport.orders.OrderTripClickedListener
import com.lod.rtviwe.tport.orders.OrdersFragment
import com.lod.rtviwe.tport.profile.ProfileFragment
import com.lod.rtviwe.tport.profile.registration.*
import com.lod.rtviwe.tport.search.SearchFragment
import com.lod.rtviwe.tport.search.SearchListener
import com.lod.rtviwe.tport.search.searchtrip.SearchTripClickedListener
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsFragment
import com.lod.rtviwe.tport.tripdetails.TripDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), RegisterStepOneListener, RegisterStepTwoListener, RegisterStepThreeListener,
    SearchListener, SearchTripClickedListener, OrderTripClickedListener {

    private var actionId = R.id.action_search
    private var fragmentOrdersTabId = R.layout.orders_fragment
    private var fragmentSearchTabId = R.layout.search_fragment
    private var fragmentBonusesTabId = R.layout.bonuses_fragment
    private var fragmentProfileTabId = R.layout.register_step_one_fragment
    private var registerPhoneNumber = ""
    private var registerCode = ""
    private var fromPlaceSearchBox = ""
    private var toPlaceSearchBox = ""
    private var travelTimeSearchBox = ""
    private var tripInSearchFragment: FullTrip? = null
    private var tripInOrdersFragment: FullTrip? = null

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottom_navigation.setOnNavigationItemSelectedListener {
            actionId = it.itemId

            if (actionId != bottom_navigation.selectedItemId) {
                setUpCurrentFragment()
            } else {
                scrollFragmentToTop()
            }

            true
        }

        bottom_navigation.selectedItemId = actionId

        if (savedInstanceState == null) {
            setUpCurrentFragment()
        }

        bottom_navigation.itemIconTintList = null
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.apply {
            putInt(STATE_ACTION_ID, actionId)
            putInt(STATE_SEARCH_LAYOUT_ID, fragmentSearchTabId)
            putInt(STATE_ORDERS_LAYOUT_ID, fragmentOrdersTabId)
            putInt(STATE_BONUSES_LAYOUT_ID, fragmentBonusesTabId)
            putInt(STATE_PROFILE_LAYOUT_ID, fragmentProfileTabId)
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
            actionId = savedInstanceState.getInt(STATE_ACTION_ID)
            fragmentSearchTabId = savedInstanceState.getInt(STATE_SEARCH_LAYOUT_ID)
            fragmentOrdersTabId = savedInstanceState.getInt(STATE_ORDERS_LAYOUT_ID)
            fragmentBonusesTabId = savedInstanceState.getInt(STATE_BONUSES_LAYOUT_ID)
            fragmentProfileTabId = savedInstanceState.getInt(STATE_PROFILE_LAYOUT_ID)
            registerPhoneNumber = savedInstanceState.getString(STATE_REGISTER_PHONE_NUMBER)
            fromPlaceSearchBox = savedInstanceState.getString(STATE_FROM_PLACE)
            toPlaceSearchBox = savedInstanceState.getString(STATE_TO_PLACE)
            travelTimeSearchBox = savedInstanceState.getString(STATE_TRAVEL_TIME)
            registerCode = savedInstanceState.getString(STATE_CODE)

            setUpCurrentFragment()
        }

        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        when (actionId) {
            R.id.action_profile -> when (fragmentProfileTabId) {
                R.layout.register_step_two_fragment -> {
                    fragmentProfileTabId = R.layout.register_step_one_fragment
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
            R.id.action_search -> when (fragmentSearchTabId) {
                R.layout.trip_details_fragment -> {
                    fragmentSearchTabId = R.layout.search_trips_fragment
                    setUpCurrentFragment()
                }
                R.layout.search_trips_fragment -> {
                    fragmentSearchTabId = R.layout.search_fragment
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
            R.id.action_orders -> when (fragmentOrdersTabId) {
                R.layout.trip_details_fragment -> {
                    fragmentOrdersTabId = R.layout.orders_fragment
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
        }
    }

    override fun onRegisterStepOneContinue(phoneNumber: String) {
        registerPhoneNumber = phoneNumber
        fragmentProfileTabId = R.layout.register_step_two_fragment
        setUpCurrentFragment()
    }

    override fun onRegisterStepTwoContinue() {
        fragmentProfileTabId = R.layout.register_step_three_fragment
        setUpCurrentFragment()
    }

    override fun onRegisterStepThreeContinue() {
        fragmentProfileTabId = R.layout.profile_fragment
        setUpCurrentFragment()
    }

    override fun openTripDetailsFragmentFromSearch(fullTrip: FullTrip) {
        fragmentSearchTabId = R.layout.trip_details_fragment
        tripInSearchFragment = fullTrip
        setUpCurrentFragment()
    }

    override fun openTripDetailFragmentFromOrder(fullTrip: FullTrip) {
        fragmentOrdersTabId = R.layout.trip_details_fragment
        tripInOrdersFragment = fullTrip
        setUpCurrentFragment()
    }

    override fun savePhoneNumber(phoneNumber: String) {
        registerPhoneNumber = phoneNumber
    }

    override fun saveCode(code: String) {
        registerCode = code
    }

    override fun onPickUpButton(fromPlace: String, toPlace: String, travelTime: String) {
        fragmentSearchTabId = R.layout.search_trips_fragment
        fromPlaceSearchBox = fromPlace
        toPlaceSearchBox = toPlace
        travelTimeSearchBox = travelTime
        setUpCurrentFragment()
    }

    private fun setUpCurrentFragment() {
        val fragmentToSet = getCurrentFragment()

        supportFragmentManager.transaction(allowStateLoss = true) {
            replace(R.id.main_container, fragmentToSet)
        }
    }

    // TODO
    private fun scrollFragmentToTop() {

    }

    private fun getCurrentFragment() = when (actionId) {
        R.id.action_bonuses -> BonusesFragment.newInstance()
        R.id.action_orders -> when (fragmentOrdersTabId) {
            R.layout.orders_fragment -> OrdersFragment.newInstance()
            R.layout.trip_details_fragment -> {
                if (tripInOrdersFragment != null) {
                    TripDetailsFragment.newInstance(tripInOrdersFragment!!)
                } else {
                    throw RuntimeException("Trip from order is null")
                }
            }
            else -> throw RuntimeException("Not permitted fragment on action Orders")
        }
        R.id.action_search -> when (fragmentSearchTabId) {
            R.layout.search_fragment -> SearchFragment.newInstance()
            R.layout.search_trips_fragment -> SearchTripsFragment.newInstance(
                fromPlaceSearchBox,
                toPlaceSearchBox,
                travelTimeSearchBox
            )
            R.layout.trip_details_fragment -> {
                if (tripInSearchFragment != null) {
                    TripDetailsFragment.newInstance(tripInSearchFragment!!)
                } else {
                    throw RuntimeException("Trip from search is null")
                }
            }
            else -> throw RuntimeException("Not permitted fragment on action Search")
        }
        R.id.action_profile -> when (fragmentProfileTabId) {
            R.layout.profile_fragment -> ProfileFragment.newInstance()
            R.layout.register_step_one_fragment -> RegisterStepOneFragment.newInstance(registerPhoneNumber)
            R.layout.register_step_two_fragment ->
                RegisterStepTwoFragment.newInstance(registerPhoneNumber, registerCode)
            R.layout.register_step_three_fragment -> RegisterStepThreeFragment.newInstance()
            else -> throw RuntimeException("Not permitted fragment on action Profile")
        }
        else -> throw RuntimeException("Unknown fragment id")
    }

    // TODO get from prefs
    private fun isUserLogged() = false

    companion object {

        private const val STATE_ACTION_ID = "CURRENT_ACTION_ID"
        private const val STATE_SEARCH_LAYOUT_ID = "CURRENT_SEARCH_LAYOUT_ID"
        private const val STATE_ORDERS_LAYOUT_ID = "CURRENT_ORDERS_LAYOUT_ID"
        private const val STATE_BONUSES_LAYOUT_ID = "CURRENT_BONUSES_LAYOUT_ID"
        private const val STATE_PROFILE_LAYOUT_ID = "CURRENT_PROFILE_LAYOUT_ID"
        private const val STATE_REGISTER_PHONE_NUMBER = "REGISTER_PHONE_NUMBER_STATE"
        private const val STATE_FROM_PLACE = "FROM_PLACE_STATE"
        private const val STATE_TO_PLACE = "TO_PLACE_STATE"
        private const val STATE_TRAVEL_TIME = "TRAVEL_TIME_STATE"
        private const val STATE_CODE = "CODE_STATE"
    }
}
