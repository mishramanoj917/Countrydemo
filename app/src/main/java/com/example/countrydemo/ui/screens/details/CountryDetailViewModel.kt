package com.example.countrydemo.ui.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrydemo.data.model.CountryList
import com.example.countrydemo.data.repository.CountriesRepository
import com.example.countrydemo.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
):ViewModel() {
    val country : MutableState<NetworkResponse<CountryList>?> = mutableStateOf(null)

    fun fetchCountry(countryName:String){
        viewModelScope.launch {
            countriesRepository.getCountry(countryName).collect{
                country.value = it
            }
        }
    }
}