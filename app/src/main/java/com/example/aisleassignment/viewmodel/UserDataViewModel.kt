package com.example.aisleassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisleassignment.model.notes.Notes
import com.example.aisleassignment.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDataViewModel : ViewModel() {

    private val _notes = MutableLiveData<Notes>()
    val notes: LiveData<Notes> = _notes

    fun getNotes(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                ApiService.retrofitBuilder.getNotes("Bearer $accessToken")
            if (result.isSuccessful) {
                _notes.postValue(result.body())
            }
        }
    }
}