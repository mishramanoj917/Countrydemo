package com.example.countrydemo.data.remote

import javax.inject.Inject

class CountriesRemoteDataSource @Inject constructor(
    private val countriesAPI: CountriesAPI
) {
    suspend fun getCountries() = countriesAPI.getCountries()

    suspend fun getCountry(name:String) = countriesAPI.getCountry(name)
}