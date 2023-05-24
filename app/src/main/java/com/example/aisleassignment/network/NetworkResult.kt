package com.example.aisleassignment.network

sealed class NetworkResult<T>(data: T? = null, message: String? = null) {
    class Success<T>(val data: T? = null) : NetworkResult<T>(data)
    class Loading<T> : NetworkResult<T>()
    class Failure<T>(val message: String?) : NetworkResult<T>(message = message)
}