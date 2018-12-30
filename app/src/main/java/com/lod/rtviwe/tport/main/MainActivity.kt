package com.lod.rtviwe.tport.main

import android.os.Bundle
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseActivity
import com.lod.rtviwe.tport.bonuses.BonusesFragment
import com.lod.rtviwe.tport.listeners.*
import com.lod.rtviwe.tport.model.FullTrip
import com.lod.rtviwe.tport.orders.OrdersFragment
import com.lod.rtviwe.tport.profile.ProfileFragment
import com.lod.rtviwe.tport.profile.registration.RegisterStepOneFragment
import com.lod.rtviwe.tport.profile.registration.RegisterStepThreeFragment
import com.lod.rtviwe.tport.profile.registration.RegisterStepTwoFragment
import com.lod.rtviwe.tport.search.SearchFragment
import com.lod.rtviwe.tport.search.searchtrip.SearchTripsFragment
import com.lod.rtviwe.tport.tripdetails.TripDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), RegisterStepOneListener, RegisterStepTwoListener, RegisterStepThreeListener,
    SearchListener, SearchTripClickedListener, OrderTripClickedListener {

    companion object {

        private const val STATE_ACTION_ID = "CURRENT_ACTION_ID"
        private const val STATE_SEARCH_LAYOUT_ID = "CURRENT_SEARCH_LAYOUT_ID"
        private const val STATE_ORDERS_LAYOUT_ID = "CURRENT_ORDERS_LAYOUT_ID"
        private const val STATE_BONUSES_LAYOUT_ID = "CURRENT_BONUSES_LAYOUT_ID"
        private const val STATE_PROFILE_LAYOUT_ID = "CURRENT_PROFILE_LAYOUT_ID"
        private const val STATE_PHONE_NUMBER = "PHONE_NUMBER_STATE"
        private const val STATE_FROM_PLACE = "FROM_PLACE_STATE"
        private const val STATE_TO_PLACE = "TO_PLACE_STATE"
        private const val STATE_TRAVEL_TIME = "TRAVEL_TIME_STATE"
        private const val STATE_CODE = "CODE_STATE"
    }

    private var currentActionId = R.id.action_search
    private var currentFragmentOrdersTabId = R.layout.orders_fragment
    private var currentFragmentSearchTabId = R.layout.search_fragment
    private var currentFragmentBonusesTabId = R.layout.bonuses_fragment
    private var currentFragmentProfileTabId = R.layout.register_step_one_fragment
    private var phoneNumber = 0L
    private var code = ""
    private var fromPlaceText = ""
    private var toPlaceText = ""
    private var travelTimeText = ""
    private var tripFromSearch: FullTrip? = null
    private var tripFromOrders: FullTrip? = null

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottom_navigation.setOnNavigationItemSelectedListener {
            currentActionId = it.itemId

            if (currentActionId != bottom_navigation.selectedItemId) {
                setUpCurrentFragment()
            } else {
                scrollFragmentToTop()
            }

            true
        }

        bottom_navigation.selectedItemId = currentActionId

        if (savedInstanceState == null) {
            setUpCurrentFragment()
        }

        bottom_navigation.itemIconTintList = null
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.apply {
            putInt(STATE_ACTION_ID, currentActionId)
            putInt(STATE_SEARCH_LAYOUT_ID, currentFragmentSearchTabId)
            putInt(STATE_ORDERS_LAYOUT_ID, currentFragmentOrdersTabId)
            putInt(STATE_BONUSES_LAYOUT_ID, currentFragmentBonusesTabId)
            putInt(STATE_PROFILE_LAYOUT_ID, currentFragmentProfileTabId)
            putLong(STATE_PHONE_NUMBER, phoneNumber)
            putString(STATE_FROM_PLACE, fromPlaceText)
            putString(STATE_TO_PLACE, toPlaceText)
            putString(STATE_TRAVEL_TIME, travelTimeText)
            putString(STATE_CODE, code)
        }

        super.onSaveInstanceState(outState)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            currentActionId = savedInstanceState.getInt(STATE_ACTION_ID)
            currentFragmentSearchTabId = savedInstanceState.getInt(STATE_SEARCH_LAYOUT_ID)
            currentFragmentOrdersTabId = savedInstanceState.getInt(STATE_ORDERS_LAYOUT_ID)
            currentFragmentBonusesTabId = savedInstanceState.getInt(STATE_BONUSES_LAYOUT_ID)
            currentFragmentProfileTabId = savedInstanceState.getInt(STATE_PROFILE_LAYOUT_ID)
            phoneNumber = savedInstanceState.getLong(STATE_PHONE_NUMBER)
            fromPlaceText = savedInstanceState.getString(STATE_FROM_PLACE)
            toPlaceText = savedInstanceState.getString(STATE_TO_PLACE)
            travelTimeText = savedInstanceState.getString(STATE_TRAVEL_TIME)
            code = savedInstanceState.getString(STATE_CODE)

            setUpCurrentFragment()
        }

        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        when (currentActionId) {
            R.id.action_profile -> when (currentFragmentProfileTabId) {
                R.layout.register_step_two_fragment -> {
                    currentFragmentProfileTabId = R.layout.register_step_one_fragment
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
            R.id.action_search -> when (currentFragmentSearchTabId) {
                R.layout.trip_details_fragment -> {
                    currentFragmentSearchTabId = R.layout.search_trips_fragment
                    setUpCurrentFragment()
                }
                R.layout.search_trips_fragment -> {
                    currentFragmentSearchTabId = R.layout.search_fragment
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
            R.id.action_orders -> when (currentFragmentOrdersTabId) {
                R.layout.trip_details_fragment -> {
                    currentFragmentOrdersTabId = R.layout.orders_fragment
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
        }
    }

    override fun onRegisterStepOneContinue(phoneNumber: Long) {
        this.phoneNumber = ("+7$phoneNumber").toLong()
        currentFragmentProfileTabId = R.layout.register_step_two_fragment
        setUpCurrentFragment()
    }

    override fun onRegisterStepTwoContinue() {
        currentFragmentProfileTabId = R.layout.register_step_three_fragment
        setUpCurrentFragment()
    }

    override fun onRegisterStepThreeContinue() {
        currentFragmentProfileTabId = R.layout.profile_fragment
        setUpCurrentFragment()
    }

    override fun openTripDetailFragmentFromSearch(fullTrip: FullTrip) {
        currentFragmentSearchTabId = R.layout.trip_details_fragment
        tripFromSearch = fullTrip
        setUpCurrentFragment()
    }

    override fun openTripDetailFragmentFromOrder(fullTrip: FullTrip) {
        currentFragmentOrdersTabId = R.layout.trip_details_fragment
        tripFromOrders = fullTrip
        setUpCurrentFragment()
    }

    override fun savePhoneNumber(phoneNumber: Long) {
        this.phoneNumber = phoneNumber
    }

    override fun saveCode(code: String) {
        this.code = code
    }

    override fun onPickUpButton(fromPlace: String, toPlace: String, travelTime: String) {
        currentFragmentSearchTabId = R.layout.search_trips_fragment
        fromPlaceText = fromPlace
        toPlaceText = toPlace
        travelTimeText = travelTime
        setUpCurrentFragment()
    }

    private fun setUpCurrentFragment() {
        val fragmentToSet = getCurrentFragment()

        supportFragmentManager.transaction(allowStateLoss = true) {
            replace(R.id.main_container, fragmentToSet)
        }
    }

    private fun scrollFragmentToTop() {
//        getCurrentFragment().scrollToTop()
    }

    private fun getCurrentFragment() = when (currentActionId) {
        R.id.action_bonuses -> BonusesFragment.newInstance()
        R.id.action_orders -> when (currentFragmentOrdersTabId) {
            R.layout.orders_fragment -> OrdersFragment.newInstance()
            R.layout.trip_details_fragment -> {
                if (tripFromOrders != null) {
                    TripDetailsFragment.newInstance(tripFromOrders!!)
                } else {
                    throw RuntimeException("Trip from order is null")
                }
            }
            else -> throw RuntimeException("Not permitted fragment on action Orders")
        }
        R.id.action_search -> when (currentFragmentSearchTabId) {
            R.layout.search_fragment -> SearchFragment.newInstance()
            R.layout.search_trips_fragment -> SearchTripsFragment.newInstance(
                fromPlaceText,
                toPlaceText,
                travelTimeText
            )
            R.layout.trip_details_fragment -> {
                if (tripFromSearch != null) {
                    TripDetailsFragment.newInstance(tripFromSearch!!)
                } else {
                    throw RuntimeException("Trip from search is null")
                }
            }
            else -> throw RuntimeException("Not permitted fragment on action Search")
        }
        R.id.action_profile -> when (currentFragmentProfileTabId) {
            R.layout.register_step_one_fragment -> RegisterStepOneFragment.newInstance(phoneNumber)
            R.layout.register_step_two_fragment -> RegisterStepTwoFragment.newInstance(phoneNumber, code)
            R.layout.register_step_three_fragment -> RegisterStepThreeFragment.newInstance()
            // TODO replace it to else -> throw RuntimeException("Not permitted fragment on action Orders")
            else -> ProfileFragment.newInstance()
        }
        else -> throw RuntimeException("Unknown fragment id")
    }
}
