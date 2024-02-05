package com.example.countrydemo.data.api

import com.example.countrydemo.data.model.Country
import com.example.countrydemo.util.Constant
import retrofit2.Response
import retrofit2.http.GET

interface CountriesAPI {
    @GET(Constant.ALL_COUNTRIES)
    suspend fun getCountries():Response<List<Country>>
}