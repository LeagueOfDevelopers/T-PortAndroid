package com.lod.rtviwe.tport.ui.activity

import android.os.Bundle
import androidx.fragment.app.transaction
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.ui.fragment.BonusesFragment
import com.lod.rtviwe.tport.ui.fragment.OrdersFragment
import com.lod.rtviwe.tport.ui.fragment.ProfileFragment
import com.lod.rtviwe.tport.ui.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {

        private const val STATE_CURRENT_FRAGMENT = "CURRENT_FRAGMENT_ID"
    }

    private val fragmentBonuses = BonusesFragment.newInstance()
    private val fragmentOrders = OrdersFragment.newInstance()
    private val fragmentSearch = SearchFragment.newInstance()
    private val fragmentProfile = ProfileFragment.newInstance()
    private var currentFragmentId = R.id.action_search

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
        super.onSaveInstanceState(outState)

        outState?.putInt(STATE_CURRENT_FRAGMENT, currentFragmentId)
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
        R.id.action_profile -> fragmentProfile
        else -> throw RuntimeException("Unknown fragment id")
    }
}
