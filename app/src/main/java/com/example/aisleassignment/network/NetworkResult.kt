package com.example.aisleassignment.network

sealed class NetworkResult<T>(data: T? = null, message: String? = null) {
    class Success<T>(private val data: T?) : NetworkResult<T>(data)
    class Loading<T> : NetworkResult<T>()
    class Failure<T>(private val message: String?) : NetworkResult<T>(message = message)
}