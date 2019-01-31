package com.lod.rtviwe.tport.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.lod.rtviwe.tport.R
import com.lod.rtviwe.tport.base.ScrollableFragment
import com.lod.rtviwe.tport.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!

        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
        navController.navigatorProvider += navigator

        navController.setGraph(R.navigation.nav_graph)

        main_bottom_navigation.setOnNavigationItemReselectedListener {
            scrollFragmentToTop()
        }

        main_bottom_navigation.itemIconTintList = null

        binding.mainBottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    private fun scrollFragmentToTop() {
        val currentFragment = nav_host_fragment.childFragmentManager.fragments[0]
        when (currentFragment) {
            is ScrollableFragment -> currentFragment.scrollToTop()
            else -> Timber.e("$currentFragment does not implement ScrollableFragment")
        }
    }
}
