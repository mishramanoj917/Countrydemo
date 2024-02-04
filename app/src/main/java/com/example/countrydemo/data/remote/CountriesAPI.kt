package com.example.countrydemo.data.remote

import com.example.countrydemo.data.model.CountryList
import com.example.countrydemo.network.Constant
import retrofit2.Response
import retrofit2.http.GET

interface CountriesAPI {
    @GET(Constant.ALL_COUNTRIES)
    suspend fun getCountries():Response<List<CountryList>>

    @GET(Constant.COUNTRY_DETAIL)
    suspend fun getCountry(name:String): Response<CountryList>
}