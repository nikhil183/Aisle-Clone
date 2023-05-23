package com.example.aisleassignment.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.aisleassignment.R
import com.example.aisleassignment.databinding.ActivityMainBinding
import com.example.aisleassignment.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initNavController()
    }

    private fun initNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fcvNavHost
        ) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun setup() {
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.loginWithPhoneNumber("+919876543212")

        viewModel.isNumberRegistered.observe(this) {
            if (it) {
                viewModel.verifyOtp("+919876543212", "1234")
            } else {
                Log.i("answer", "not registered")
            }
        }
    }
}