package com.example.countrydemo.ui.screens.countries

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countrydemo.network.NetworkResponse
import com.example.countrydemo.ui.screens.components.CircularIndeterminateProgressBar
import com.example.countrydemo.ui.screens.components.CountryCardNew
import com.example.countrydemo.ui.screens.components.appbar.HomeAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(selectedCountry: (String) -> Unit) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val progressBar = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = 0) {
        mainViewModel.countryList()
    }

    mainViewModel.response.value.let {
        when (it) {
            is NetworkResponse.Success -> {
                progressBar.value = false
                val countries = it.data as ArrayList
                Scaffold(
                    topBar = {
                        HomeAppBar(title = "Counties")
                    }
                ) {
                    LazyColumn(
                        Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(countries) { it ->
                            CountryCardNew(
                                selectedCountry = selectedCountry,
                                imagePath = it.flags.png,
                                commonName = it.name.common,
                                capital = it.capital[0],
                                status = it.status,
                                timezones = it.timezones[0]
                            )
                        }
                    }
                }
            }

            is NetworkResponse.Error -> {
                progressBar.value = false
                Text(text = it.exception)
            }

            NetworkResponse.Loading -> {
                progressBar.value = true
                CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
            }

            else -> {}
        }
    }
}

