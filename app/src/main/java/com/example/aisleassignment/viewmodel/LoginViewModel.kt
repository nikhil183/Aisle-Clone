package com.example.aisleassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisleassignment.model.otp.OtpVerificationInput
import com.example.aisleassignment.model.login.PhoneNumberLoginInput
import com.example.aisleassignment.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _isNumberRegistered = MutableLiveData<Boolean>()
    val isNumberRegistered: LiveData<Boolean> = _isNumberRegistered

    private val _accessToken = MutableLiveData<String>()
    val accessToken: LiveData<String> = _accessToken

    fun loginWithPhoneNumber(phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                ApiService.retrofitBuilder.isNumberRegistered(PhoneNumberLoginInput(phoneNumber))
            if (result.isSuccessful) {
                _isNumberRegistered.postValue(result.body()!!.status)
            }
        }
    }

    fun verifyOtp(phoneNumber: String, otp: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                ApiService.retrofitBuilder.verifyOtp(OtpVerificationInput(phoneNumber, otp))
            if (result.isSuccessful) {
                result.body()?.let { getNotes(it.token) }
            }
        }
    }

    fun getNotes(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                ApiService.retrofitBuilder.getProfileList("Bearer $accessToken")
            if (result.isSuccessful) {
                Log.i( "photo", result.body()?.invites?.profiles?.get(0)?.photos?.get(0)?.photo.toString())
            }
        }
    }

}