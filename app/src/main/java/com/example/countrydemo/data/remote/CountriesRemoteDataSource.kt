package com.example.countrydemo.data.remote

import com.example.countrydemo.data.api.CountriesAPI
import javax.inject.Inject

class CountriesRemoteDataSource @Inject constructor(
    private val countriesAPI: CountriesAPI
) {
    suspend fun getCountries() = countriesAPI.getCountries()
}