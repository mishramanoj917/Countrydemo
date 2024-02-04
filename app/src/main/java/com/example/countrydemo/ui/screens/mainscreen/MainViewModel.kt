package com.example.countrydemo.ui.screens.mainscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrydemo.data.model.CountryList
import com.example.countrydemo.data.repository.CountriesRepository
import com.example.countrydemo.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: CountriesRepository
):ViewModel() {

    val countryList : MutableState<NetworkResponse<List<CountryList>>?> = mutableStateOf(null)

    fun countryList(){
        viewModelScope.launch {
            repo.getCountries().collect{
                countryList.value = it
            }
        }
    }
}