package com.lod.rtviwe.tport.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.BaseActivity
import com.lod.rtviwe.tport.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(main_bottom_navigation, navController)

        main_bottom_navigation.setOnNavigationItemReselectedListener {
            scrollFragmentToTop()
        }

        main_bottom_navigation.itemIconTintList = null
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    private fun scrollFragmentToTop() {
        val currentFragment = nav_host_fragment.childFragmentManager.fragments[0]
        when (currentFragment) {
            is BaseFragment -> currentFragment.scrollToTop()
            else -> Timber.e("$currentFragment does not implement ScrollableFragment")
        }
    }
}
