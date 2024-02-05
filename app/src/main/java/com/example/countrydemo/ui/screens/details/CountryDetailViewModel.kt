package com.example.countrydemo.ui.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrydemo.data.model.Country
import com.example.countrydemo.data.repository.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
):ViewModel() {
    val country: MutableState<Country?> = mutableStateOf(null)

    fun fetchCountry(countryName: String) {
        viewModelScope.launch {
            countriesRepository.getCountry(countryName).collect {
                country.value = it
            }
        }
    }
}