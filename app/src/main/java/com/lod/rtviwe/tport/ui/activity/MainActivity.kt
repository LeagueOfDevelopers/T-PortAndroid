package com.lod.rtviwe.tport.ui.activity

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {

        private const val STATE_CURRENT_FRAGMENT = "CURRENT_FRAGMENT_ID"
        var currentRegistrationStep = 1
    }

    private val fragmentBonuses = BonusesFragment.newInstance()
    private val fragmentOrders = OrdersFragment.newInstance()
    private val fragmentSearch = SearchFragment.newInstance()
    private val fragmentProfile = ProfileFragment.newInstance()
    private val registerStepOneFragment = RegisterStepOneFragment.newInstance()
    private val registerStepTwoFragment = RegisterStepTwoFragment.newInstance()
    private val registerStepThreeFragment = RegisterStepThreeFragment.newInstance()
    private var currentFragmentId = R.id.action_search

    private var isUserEntered: Boolean = false
        get() = false

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
    }

    override fun onResume() {
        super.onResume()
        setUpCurrentFragment()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            currentFragmentId = savedInstanceState.getInt(STATE_CURRENT_FRAGMENT)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(STATE_CURRENT_FRAGMENT, currentFragmentId)

        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.register_action_bar_menu, menu)
        return true
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
            3 -> {
                currentRegistrationStep = 2
                setUpCurrentFragment()
            }
            else -> super.onBackPressed()
        }
    }

    private fun setUpCurrentFragment() {
        val fragmentToSet = getCurrentFragment()

        supportFragmentManager.transaction(allowStateLoss = true) {
            replace(R.id.main_container, fragmentToSet)
        }
    }

    private fun scrollFragmentToTop() {
        getCurrentFragment().scrollToTop()
    }

    private fun getCurrentFragment() = when (currentFragmentId) {
        R.id.action_bonuses -> fragmentBonuses
        R.id.action_orders -> fragmentOrders
        R.id.action_search -> fragmentSearch
        R.id.action_profile -> {
            if (isUserEntered) {
                fragmentProfile
            } else {
                when (currentRegistrationStep) {
                    1 -> registerStepOneFragment
                    2 -> registerStepTwoFragment
                    3 -> registerStepThreeFragment
                    else -> throw RuntimeException("Unknown registration step")
                }
            }
        }
        else -> throw RuntimeException("Unknown fragment id")
    }
}
