package com.example.aisleassignment.view.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aisleassignment.R
import com.example.aisleassignment.constant.SharedPrefConstant.ACCESS_TOKEN
import com.example.aisleassignment.constant.SharedPrefConstant.SHARED_PREF_NAME
import com.example.aisleassignment.databinding.ActivityMainBinding
import com.example.aisleassignment.view.user.UserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        if (sharedPref.contains(ACCESS_TOKEN)) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }
}