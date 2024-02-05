package com.example.countrydemo.network

sealed class NetworkResponse<out R> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val exception: String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}
