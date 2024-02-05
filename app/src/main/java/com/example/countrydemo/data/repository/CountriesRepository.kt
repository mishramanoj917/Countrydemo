package com.example.countrydemo.data.repository

import com.example.countrydemo.data.local.CountriesLocalDataSource
import com.example.countrydemo.data.model.Country
import com.example.countrydemo.data.remote.CountriesRemoteDataSource
import com.example.countrydemo.network.NetworkCallWrapper
import com.example.countrydemo.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val countriesRemoteDataSource: CountriesRemoteDataSource,
    private val countriesLocalDataSource: CountriesLocalDataSource
): NetworkCallWrapper() {
    suspend fun getCountries(): Flow<NetworkResponse<List<Country>>> = flow {
        emit(NetworkResponse.Loading)
        try {
            emit(safeApiCall { countriesRemoteDataSource.getCountries() })
        } catch (e: Exception) {
            emit(NetworkResponse.Error(exception = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getCountry(name: String): Flow<NetworkResponse<Country>> = flow {
        emit(NetworkResponse.Loading)
        try {
            val country = countriesLocalDataSource.getCountry(name)
            if (country == null) {
                emit(NetworkResponse.Error(exception = "no data available"))
            } else
                emit(getCountry(country = country))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(exception = e.message.toString()))
        }
    }

    private fun getCountry(country: Country): NetworkResponse<Country> =
        NetworkResponse.Success(country)
}