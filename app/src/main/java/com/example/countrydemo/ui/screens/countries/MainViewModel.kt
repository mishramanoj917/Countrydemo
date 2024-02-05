package com.example.countrydemo.ui.screens.countries

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrydemo.data.model.Country
import com.example.countrydemo.data.repository.CountriesRepository
import com.example.countrydemo.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: CountriesRepository
):ViewModel() {

    val response: MutableState<NetworkResponse<List<Country>>?> = mutableStateOf(null)
    fun countryList() {
        viewModelScope.launch {
            repo.getCountries().collect {
                response.value = it
            }
        }
    }
}