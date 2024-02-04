package com.example.countrydemo.data.repository

import com.example.countrydemo.data.model.CountryList
import com.example.countrydemo.data.model.Name
import com.example.countrydemo.data.remote.CountriesRemoteDataSource
import com.example.countrydemo.network.NetworkCallWrapper
import com.example.countrydemo.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val countriesRemoteDataSource: CountriesRemoteDataSource
): NetworkCallWrapper() {

    suspend fun getCountries(): Flow<NetworkResponse<List<CountryList>>> {
        return flow {
            emit(safeApiCall { countriesRemoteDataSource.getCountries() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCountry(name: String): Flow<NetworkResponse<CountryList>> {
        return flow {
            emit(safeApiCall { countriesRemoteDataSource.getCountry(name) })
        }.flowOn(Dispatchers.IO)
    }
}