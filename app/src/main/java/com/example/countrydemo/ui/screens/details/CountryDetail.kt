package com.example.countrydemo.ui.screens.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.countrydemo.R
import com.example.countrydemo.data.model.Country
import com.example.countrydemo.ui.screens.components.AboutText
import com.example.countrydemo.ui.screens.components.CountryInfo
import com.example.countrydemo.ui.screens.components.appbar.DetailAppBar
import com.example.countrydemo.ui.theme.DefaultBackgroundColor
import com.example.countrydemo.ui.theme.FontColor
import com.example.countrydemo.ui.theme.SecondaryFontColor

@Composable
fun CountryDetail(
    countryName:String,
    navigationUp: () -> Unit
    ) {
    val detailViewModel = hiltViewModel<CountryDetailViewModel>()
    LaunchedEffect(key1 = 0) {
        detailViewModel.fetchCountry(countryName)
    }
    detailViewModel.country.value.let {
        if (it != null) {
            AnimatedVisibility(visible = true, enter = expandVertically(
                expandFrom = Alignment.Top,
                initialHeight = { 0 }
            )) {
                SetCountryDetail(country = it, navigationUp = navigationUp)
            }
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
                    CountryHeader(
                        name = country.name.common,
                        navigationUp = navigationUp
                    )  }
                item {
                    CountryBody(country = country)
                }
            }
        }
    }
}

@Composable
fun CountryHeader(
    name:String,
    navigationUp: () -> Unit
) {
    DetailAppBar(title = name, pressOnBack = navigationUp)
}
@Composable
fun CountryBody(
    country:Country
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                DefaultBackgroundColor
            )
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        Row() {
            AsyncImage(
                model = country.coatOfArms.png,
                contentDescription = null,
                modifier = Modifier
                    .height(250.dp)
                    .width(190.dp),
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.placeholder)
            )
            Column {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = country.name.common,
                    color = FontColor,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
                CountryInfo(
                    title = stringResource(id = R.string.official),
                    info = country.name.official
                )
                CountryInfo(
                    title = stringResource(id = R.string.capital),
                    info = country.capital[0].toString()
                )
                CountryInfo(
                    title = stringResource(id = R.string.population),
                    info = country.population.toString()
                )
                CountryInfo(
                    title = stringResource(id = R.string.status),
                    info = country.status
                )
                CountryInfo(
                    title = stringResource(id = R.string.timezone),
                    info = country.timezones[0]
                )
                CountryInfo(
                    title = stringResource(id = R.string.region),
                    info = country.region
                )
            }
        }
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(R.string.about),
            color = SecondaryFontColor,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
        AboutText(
            text = country.flags.alt
        )
    }
}


