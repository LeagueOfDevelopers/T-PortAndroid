package com.lod.rtviwe.tport.ui.activity

import android.os.Bundle
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.fragment.BonusesFragment
import com.lod.rtviwe.tport.ui.fragment.OrdersFragment
import com.lod.rtviwe.tport.ui.fragment.ProfileFragment
import com.lod.rtviwe.tport.ui.fragment.registration.RegisterStepOneFragment
import com.lod.rtviwe.tport.ui.fragment.registration.RegisterStepThreeFragment
import com.lod.rtviwe.tport.ui.fragment.registration.RegisterStepTwoFragment
import com.lod.rtviwe.tport.ui.fragment.search.SearchFragment
import com.lod.rtviwe.tport.ui.fragment.search.SearchRoutesFragment
import com.lod.rtviwe.tport.ui.listeners.OnRegisterStepOneListener
import com.lod.rtviwe.tport.ui.listeners.OnRegisterStepThreeListener
import com.lod.rtviwe.tport.ui.listeners.OnRegisterStepTwoListener
import com.lod.rtviwe.tport.ui.listeners.SearchListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), OnRegisterStepOneListener, OnRegisterStepTwoListener, OnRegisterStepThreeListener,
    SearchListener {

    companion object {

        private const val STATE_CURRENT_FRAGMENT = "CURRENT_FRAGMENT_ID"
        private const val STATE_REGISTRATION_STEP = "CURRENT_REGISTRATION_STEP"
        private const val STATE_SEARCH_STEP = "CURRENT_SEARCH_STEP"
        private const val STATE_PHONE_NUMBER = "PHONE_NUMBER_STATE"
        private const val STATE_FROM_PLACE = "FROM_PLACE_STATE"
        private const val STATE_TO_PLACE = "TO_PLACE_STATE"
        private const val STATE_CODE = "CODE_STATE"
    }

    private var currentRegistrationStep = 1
    private var currentSearchStep = 1
    private var currentFragmentId = R.id.action_search
    private var phoneNumber = 0L
    private var code = ""
    private var fromPlaceText = ""
    private var toPlaceText = ""

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottom_navigation.setOnNavigationItemSelectedListener {
            currentFragmentId = it.itemId

            if (currentFragmentId != bottom_navigation.selectedItemId) {
                setUpCurrentFragment()
            } else {
                scrollFragmentToTop()
            }

            true
        }

        bottom_navigation.selectedItemId = currentFragmentId

        if (savedInstanceState == null) {
            setUpCurrentFragment()
        }

        bottom_navigation.itemIconTintList = null
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.apply {
            putInt(STATE_CURRENT_FRAGMENT, currentFragmentId)
            putInt(STATE_REGISTRATION_STEP, currentRegistrationStep)
            putInt(STATE_SEARCH_STEP, currentSearchStep)
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
            currentFragmentId = savedInstanceState.getInt(STATE_CURRENT_FRAGMENT)
            currentRegistrationStep = savedInstanceState.getInt(STATE_REGISTRATION_STEP)
            currentSearchStep = savedInstanceState.getInt(STATE_SEARCH_STEP)
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
        when (currentFragmentId) {
            R.id.action_profile -> when (currentRegistrationStep) {
                2 -> {
                    currentRegistrationStep = 1
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
            R.id.action_search -> when (currentSearchStep) {
                2 -> {
                    currentSearchStep = 1
                    setUpCurrentFragment()
                }
                else -> super.onBackPressed()
            }
        }
    }

    override fun onRegisterStepOneContinue(phoneNumber: Long) {
        this.phoneNumber = ("+7$phoneNumber").toLong()
        currentRegistrationStep = 2
        setUpCurrentFragment()
    }

    override fun onRegisterStepTwoContinue() {
        currentRegistrationStep = 3
        setUpCurrentFragment()
    }

    override fun onRegisterStepThreeContinue() {
        currentRegistrationStep = 0
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

    private fun getCurrentFragment() = when (currentFragmentId) {
        R.id.action_bonuses -> BonusesFragment.newInstance()
        R.id.action_orders -> OrdersFragment.newInstance()
        R.id.action_search -> when (currentSearchStep) {
            1 -> SearchFragment.newInstance()
            2 -> SearchRoutesFragment.newInstance(fromPlaceText, toPlaceText)
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
