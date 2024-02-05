package com.example.countrydemo.ui.screens.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countrydemo.data.model.Country
import com.example.countrydemo.network.NetworkResponse
import com.example.countrydemo.ui.screens.components.CircularIndeterminateProgressBar
import com.example.countrydemo.ui.screens.components.CountryBody
import com.example.countrydemo.ui.screens.components.appbar.DetailAppBar

@Composable
fun CountryDetail(
    countryName:String,
    navigationUp: () -> Unit
    ) {

    val detailViewModel = hiltViewModel<CountryDetailViewModel>()
    val progressBar = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = 0) {
        detailViewModel.fetchCountry(countryName)
    }

    detailViewModel.country.value.let {
        when(it){
            is NetworkResponse.Error -> {
                progressBar.value = false
                Text(text = it.exception)
            }
            NetworkResponse.Loading -> {
                progressBar.value = true
                CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
            }
            is NetworkResponse.Success -> {
                progressBar.value = false
                AnimatedVisibility(visible = true, enter = expandVertically(
                    expandFrom = Alignment.Top,
                    initialHeight = { 0 }
                )) {
                    SetCountryDetail(country = it.data, navigationUp = navigationUp)
                }
            }
            else -> { }
        }
    }
}

@Composable
fun SetCountryDetail(
    country: Country,
    navigationUp: () -> Unit
){
    Surface {
        Column(modifier = Modifier.fillMaxWidth()) {
            LazyColumn{
                item {
                    DetailAppBar(title = country.name.common, pressOnBack = navigationUp)
                }
                item {
                    CountryBody(country = country)
                }
            }
        }
    }
}



