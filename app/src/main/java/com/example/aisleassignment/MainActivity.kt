package com.example.aisleassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aisleassignment.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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