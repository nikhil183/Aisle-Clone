package com.example.aisleassignment.view.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.aisleassignment.R
import com.example.aisleassignment.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityUserBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)

        initNavController()
    }

    private fun initNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fcvNavHost
        ) as NavHostFragment
        navController = navHostFragment.navController
        dataBinding.bottomNav.setupWithNavController(navController)
    }
}