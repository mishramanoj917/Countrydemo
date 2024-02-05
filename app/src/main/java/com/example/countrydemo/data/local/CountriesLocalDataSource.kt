package com.example.countrydemo.data.local

import com.example.countrydemo.data.model.Country
import com.example.countrydemo.util.ILocalStorage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class CountriesLocalDataSource @Inject constructor(
) {
    @Inject
    lateinit var prefStorage: ILocalStorage
    suspend fun getCountries() = getCountriesFromLocalStorage()

    private suspend fun getCountriesFromLocalStorage(): List<Country> {
        val jsonString = prefStorage.getData(defaultValue = "")
        val type = object : TypeToken<List<Country>>() {}.type
        return Gson().fromJson(jsonString, type)
    }

    suspend fun getCountry(name: String): Country? {
        val jsonString = prefStorage.getData(defaultValue = "")
        val type = object : TypeToken<List<Country>>() {}.type
        val countries = Gson().fromJson(jsonString, type) as List<Country>
        return countries.let { it.find { item -> item.name.common == name } }

    }
}