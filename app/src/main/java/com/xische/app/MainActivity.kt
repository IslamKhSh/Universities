package com.xische.app

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.xische.universities.R
import com.xische.universities.databinding.ActivityMainBinding
import com.xische.universities.feature.common.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes: Int = R.layout.activity_main

    private lateinit var navController: NavController

    override fun init() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    /**
     * when user click on up button navigate up.
     */
    override fun onSupportNavigateUp() = navController.navigateUp()
}
