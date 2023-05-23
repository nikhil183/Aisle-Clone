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

    lateinit var phoneNumber: String
    lateinit var countryCode: String
    private lateinit var otp: String
    lateinit var accessToken: String

    private val _isNumberRegistered = MutableLiveData<Boolean>()
    val isNumberRegistered: LiveData<Boolean> = _isNumberRegistered

    private val _isOtpValid = MutableLiveData<Boolean>()
    val isOtpValid: LiveData<Boolean> = _isOtpValid

    fun isValidPhoneNumber(countryCode: String, phoneNumber: String): Boolean {
        if(phoneNumber.length==10) {
            this.phoneNumber = phoneNumber
            this.countryCode = countryCode
            return true
        }
        return false
    }

    fun loginWithPhoneNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                ApiService.retrofitBuilder.isNumberRegistered(PhoneNumberLoginInput(countryCode+phoneNumber))
            if (result.isSuccessful) {
                _isNumberRegistered.postValue(result.body()!!.status)
            }
        }
    }

    fun isOtpFormatValid(otp: String): Boolean {
        if(otp.length==4) {
            this.otp = otp
            return true
        }
        return false
    }

    fun verifyOtp() {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                ApiService.retrofitBuilder.verifyOtp(OtpVerificationInput(countryCode+phoneNumber, otp))
            if (result.isSuccessful) {
                _isOtpValid.postValue(true)
                accessToken = result.body()?.token.toString()
            } else {
                _isOtpValid.postValue(false)
            }
        }
    }
}