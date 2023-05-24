package com.example.aisleassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisleassignment.constant.AppConstants.AUTHORIZATION_PREFIX
import com.example.aisleassignment.model.notes.Notes
import com.example.aisleassignment.network.ApiService
import com.example.aisleassignment.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDataViewModel: ViewModel() {

    private val _notes = MutableLiveData<NetworkResult<Notes>>()
    val notes: LiveData<NetworkResult<Notes>> = _notes

    fun getNotes(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _notes.postValue(NetworkResult.Loading())
            val result =
                ApiService.retrofitBuilder.getNotes("$AUTHORIZATION_PREFIX $accessToken")
            if (result.isSuccessful) {
                _notes.postValue(NetworkResult.Success(result.body()))
            } else {
                _notes.postValue(NetworkResult.Failure(result.message()))
            }
        }
    }
}