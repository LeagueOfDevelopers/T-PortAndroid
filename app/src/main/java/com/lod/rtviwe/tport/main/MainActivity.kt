package com.lod.rtviwe.tport.main

import android.os.Bundle
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.bonuses.BonusesFragment
import com.lod.rtviwe.tport.listeners.*
import com.lod.rtviwe.tport.orders.OrdersFragment
import com.lod.rtviwe.tport.profile.ProfileFragment
import com.lod.rtviwe.tport.profile.registration.RegisterStepOneFragment
import com.lod.rtviwe.tport.profile.registration.RegisterStepThreeFragment
import com.lod.rtviwe.tport.profile.registration.RegisterStepTwoFragment
import com.lod.rtviwe.tport.search.SearchFragment
import com.lod.rtviwe.tport.search.searchroute.SearchRoutesFragment
import com.lod.rtviwe.tport.tripdetails.TripDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), OnRegisterStepOneListener, OnRegisterStepTwoListener, OnRegisterStepThreeListener,
    SearchListener, SearchTripClickedListener, OrderTripClickedListener {

    companion object {

        private const val STATE_CURRENT_FRAGMENT = "CURRENT_FRAGMENT_ID"
        private const val STATE_REGISTRATION_STEP = "CURRENT_REGISTRATION_STEP"
        private const val STATE_ORDER_STEP = "CURRENT_ORDER_STEP"
        private const val STATE_SEARCH_STEP = "CURRENT_SEARCH_STEP"
        private const val STATE_PHONE_NUMBER = "PHONE_NUMBER_STATE"
        private const val STATE_FROM_PLACE = "FROM_PLACE_STATE"
        private const val STATE_TO_PLACE = "TO_PLACE_STATE"
        private const val STATE_CODE = "CODE_STATE"
    }

    private var currentRegistrationStep = 1
    private var currentSearchStep = 1
    private var currentOrderStep = 1
    private var currentActionId = R.id.action_search
    private var currentFragmentLayoutId = R.layout.search_fragment
    private var phoneNumber = 0L
    private var code = ""
    private var fromPlaceText = ""
    private var toPlaceText = ""

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
            putInt(STATE_CURRENT_FRAGMENT, currentActionId)
            putInt(STATE_REGISTRATION_STEP, currentRegistrationStep)
            putInt(STATE_SEARCH_STEP, currentSearchStep)
            putInt(STATE_ORDER_STEP, currentOrderStep)
            putLong(STATE_PHONE_NUMBER, phoneNumber)
            putString(STATE_FROM_PLACE, fromPlaceText)
            putString(STATE_TO_PLACE, toPlaceText)
            putString(STATE_CODE, code)
        }

        super.onSaveInstanceState(outState)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            currentActionId = savedInstanceState.getInt(STATE_CURRENT_FRAGMENT)
            currentRegistrationStep = savedInstanceState.getInt(STATE_REGISTRATION_STEP)
            currentSearchStep = savedInstanceState.getInt(STATE_SEARCH_STEP)
            currentOrderStep = savedInstanceState.getInt(STATE_ORDER_STEP)
            phoneNumber = savedInstanceState.getLong(STATE_PHONE_NUMBER)
            fromPlaceText = savedInstanceState.getString(STATE_FROM_PLACE)
            toPlaceText = savedInstanceState.getString(STATE_TO_PLACE)
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
            R.id.action_profile -> when (currentRegistrationStep) {
                2 -> {
                    currentRegistrationStep = 1
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
            R.id.action_search -> when (currentSearchStep) {
                3 -> {
                    currentSearchStep = 2
                    setUpCurrentFragment()
                }
                2 -> {
                    currentSearchStep = 1
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
            R.id.action_orders -> when (currentOrderStep) {
                2 -> {
                    currentOrderStep = 1
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
        }
    }

    override fun onRegisterStepOneContinue(phoneNumber: Long) {
        this.phoneNumber = ("+7$phoneNumber").toLong()
        currentRegistrationStep = 2
        currentFragmentLayoutId = R.layout.register_step_two_fragment
        setUpCurrentFragment()
    }

    override fun onRegisterStepTwoContinue() {
        currentRegistrationStep = 3
        currentFragmentLayoutId = R.layout.register_step_three_fragment
        setUpCurrentFragment()
    }

    override fun onRegisterStepThreeContinue() {
        currentRegistrationStep = 0
        currentFragmentLayoutId = R.layout.profile_fragment
        setUpCurrentFragment()
    }

    override fun openTripDetailFragmentFromSearch() {
        currentSearchStep = 3
        currentFragmentLayoutId = R.layout.trip_details_fragment
        setUpCurrentFragment()
    }

    override fun openTripDetailFragmentFromOrder() {
        currentOrderStep = 2
        currentFragmentLayoutId = R.layout.trip_details_fragment
        setUpCurrentFragment()
    }

    override fun savePhoneNumber(phoneNumber: Long) {
        this.phoneNumber = phoneNumber
    }

    override fun saveCode(code: String) {
        this.code = code
    }

    override fun onPickUpButton(fromPlace: String, toPlace: String) {
        currentSearchStep = 2
        currentFragmentLayoutId = R.layout.search_routes_fragment
        fromPlaceText = fromPlace
        toPlaceText = toPlace
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
        R.id.action_orders -> when (currentOrderStep) {
            1 -> OrdersFragment.newInstance()
            2 -> TripDetailsFragment.newInstance()
            else -> OrdersFragment.newInstance()
        }
        R.id.action_search -> when (currentSearchStep) {
            1 -> SearchFragment.newInstance()
            2 -> SearchRoutesFragment.newInstance(fromPlaceText, toPlaceText)
            3 -> TripDetailsFragment.newInstance()
            else -> SearchFragment.newInstance()
        }
        R.id.action_profile -> when (currentRegistrationStep) {
            1 -> RegisterStepOneFragment.newInstance(phoneNumber)
            2 -> RegisterStepTwoFragment.newInstance(phoneNumber, code)
            3 -> RegisterStepThreeFragment.newInstance()
            else -> ProfileFragment.newInstance()
        }
        else -> throw RuntimeException("Unknown fragment id")
    }
}
