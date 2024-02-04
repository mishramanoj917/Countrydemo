package com.example.countrydemo.ui.screens.mainscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countrydemo.network.NetworkResponse
import com.example.countrydemo.ui.screens.components.appbar.HomeAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(selectedCountry: (String) -> Unit) {
    val mainViewModel = hiltViewModel<MainViewModel>()

    LaunchedEffect(key1 = 0) {
        mainViewModel.countryList()
    }

    when(mainViewModel.countryList.value){
        is NetworkResponse.Success-> {
            val countries= mainViewModel.countryList.value?.data as ArrayList
            Scaffold(
                topBar = {
                    HomeAppBar(title = "Counties")
                }
            ) {
                LazyColumn(
                    Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ){
                    items(countries){it->
                        CountryCard(
                            selectedCountry = selectedCountry,
                            imagePath = it.flags.png,
                            commonName = it.name.common,
                            capital = it.capital[0]
                        )
                    }
                }
            }
        }
        is NetworkResponse.Error ->{

        }
        is NetworkResponse.Loading ->{

        }
        else -> {}
    }
}
