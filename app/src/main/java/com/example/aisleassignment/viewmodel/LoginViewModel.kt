package com.example.aisleassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisleassignment.model.login.PhoneNumberLoginInput
import com.example.aisleassignment.model.otp.OtpVerificationInput
import com.example.aisleassignment.network.ApiService
import com.example.aisleassignment.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    lateinit var phoneNumber: String
    lateinit var countryCode: String
    private lateinit var otp: String

    private val _isNumberRegistered = MutableLiveData<NetworkResult<Boolean>>()
    val isNumberRegistered: LiveData<NetworkResult<Boolean>> = _isNumberRegistered

    private val _accessToken = MutableLiveData<NetworkResult<String>>()
    val accessToken: LiveData<NetworkResult<String>> = _accessToken

    fun isValidPhoneNumber(countryCode: String, phoneNumber: String): Boolean {
        if (phoneNumber.length == 10) {
            this.phoneNumber = phoneNumber
            this.countryCode = countryCode
            return true
        }
        return false
    }

    fun loginWithPhoneNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            _isNumberRegistered.postValue(NetworkResult.Loading())
            val result =
                ApiService.retrofitBuilder.isNumberRegistered(PhoneNumberLoginInput(countryCode + phoneNumber))
            if (result.isSuccessful && result.body()!!.status) {
                _isNumberRegistered.postValue(NetworkResult.Success())
            } else {
                _isNumberRegistered.postValue(NetworkResult.Failure(result.message()))
            }
        }
    }

    fun isOtpFormatValid(otp: String): Boolean {
        if (otp.length == 4) {
            this.otp = otp
            return true
        }
        return false
    }

    fun verifyOtp() {
        viewModelScope.launch(Dispatchers.IO) {
            _accessToken.postValue(NetworkResult.Loading())
            val result =
                ApiService.retrofitBuilder.verifyOtp(
                    OtpVerificationInput(
                        countryCode + phoneNumber,
                        otp
                    )
                )
            if (result.isSuccessful && result.body()?.token != null) {
                _accessToken.postValue(NetworkResult.Success(result.body()?.token))
            } else {
                _accessToken.postValue(NetworkResult.Failure(result.message()))
            }
        }
    }
}