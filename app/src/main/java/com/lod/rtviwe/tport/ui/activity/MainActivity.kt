package com.lod.rtviwe.tport.ui.activity

import android.os.Bundle
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.fragment.*
import com.lod.rtviwe.tport.ui.listeners.OnRegisterStepOneListener
import com.lod.rtviwe.tport.ui.listeners.OnRegisterStepThreeListener
import com.lod.rtviwe.tport.ui.listeners.OnRegisterStepTwoListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), OnRegisterStepOneListener, OnRegisterStepTwoListener, OnRegisterStepThreeListener {

    companion object {

        private const val STATE_CURRENT_FRAGMENT = "CURRENT_FRAGMENT_ID"
        private const val STATE_REGISTRATION_STEP = "CURRENT_REGISTRATION_ID"
        private const val STATE_PHONE_NUMBER = "PHONE_NUMBER_STATE"
        private const val STATE_CODE = "CODE_STATE"
    }

    private var currentRegistrationStep = 1
    private var currentFragmentId = R.id.action_search
    private var isUserEntered = false
    private var phoneNumber = 0L
    private var code = 0

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
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.apply {
            putInt(STATE_CURRENT_FRAGMENT, currentFragmentId)
            putInt(STATE_REGISTRATION_STEP, currentRegistrationStep)
            putLong(STATE_PHONE_NUMBER, phoneNumber)
            putInt(STATE_CODE, code)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            currentFragmentId = savedInstanceState.getInt(STATE_CURRENT_FRAGMENT)
            currentRegistrationStep = savedInstanceState.getInt(STATE_REGISTRATION_STEP)
            phoneNumber = savedInstanceState.getLong(STATE_PHONE_NUMBER)
            code = savedInstanceState.getInt(STATE_CODE)

            setUpCurrentFragment()
        }

        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        when (currentRegistrationStep) {
            2 -> {
                currentRegistrationStep = 1
                setUpCurrentFragment()
            }
            else -> super.onBackPressed()
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

    override fun saveCode(code: Int) {
        this.code = code
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
        R.id.action_search -> SearchFragment.newInstance()
        R.id.action_profile -> {
            if (isUserEntered) {
                ProfileFragment.newInstance()
            } else {
                when (currentRegistrationStep) {
                    // TODO remove it
                    0 -> ProfileFragment.newInstance()
                    1 -> RegisterStepOneFragment.newInstance(phoneNumber)
                    2 -> RegisterStepTwoFragment.newInstance(phoneNumber, code)
                    3 -> RegisterStepThreeFragment.newInstance()
                    else -> throw RuntimeException("Unknown registration step")
                }
            }
        }
        else -> throw RuntimeException("Unknown fragment id")
    }
}
