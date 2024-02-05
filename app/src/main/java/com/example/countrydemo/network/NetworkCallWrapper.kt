package com.example.countrydemo.network

import com.example.countrydemo.util.ILocalStorage
import com.google.gson.Gson
import com.google.gson.JsonArray
import retrofit2.Response
import javax.inject.Inject

abstract class NetworkCallWrapper {

    @Inject
    lateinit var prefStorage: ILocalStorage
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResponse<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val res = Gson().toJsonTree(body) as JsonArray
                    prefStorage.saveData(res.toString())
                    return NetworkResponse.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResponse<T> =
        NetworkResponse.Error("Api call failed $errorMessage")

}