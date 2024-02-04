package com.example.countrydemo.ui.screens.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countrydemo.R
import com.example.countrydemo.data.model.CountryList
import com.example.countrydemo.network.NetworkResponse
import com.example.countrydemo.ui.screens.components.appbar.DetailAppBar
import com.example.countrydemo.ui.theme.DefaultBackgroundColor
import com.example.countrydemo.ui.theme.FontColor
import com.example.countrydemo.ui.theme.SecondaryFontColor
import com.example.countrydemo.ui.theme.aboutText
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin


@Composable
fun CountryDetail(
    countryName:String,
    navigationUp: () -> Unit
    ) {
    val detailViewModel = hiltViewModel<CountryDetailViewModel>()
    LaunchedEffect(key1 = 0) {
        detailViewModel.fetchCountry("${countryName}+?fullText=true")
    }
    when (detailViewModel.country.value) {
        is NetworkResponse.Success -> {
            AnimatedVisibility(visible = true, enter = expandVertically(
                expandFrom = Alignment.Top,
                initialHeight = {0}
            ) ) {
                SetCountryDetail(navigationUp = navigationUp)
            }
        }
        is NetworkResponse.Loading -> {}
        is NetworkResponse.Error -> {
            SetCountryDetail(navigationUp = navigationUp)
            Text(text = "error received")
        }
        else -> {}
    }
}

@Composable
fun SetCountryDetail(
    navigationUp: () -> Unit
){
    Text(text = "welcome....")
    Surface {
        Column(modifier = Modifier.fillMaxWidth()) {
            LazyColumn{
                item {
                    CountryHeader(
                        countryImage = "https://mainfacts.com/media/images/coats_of_arms/in.png",
                        navigationUp = navigationUp
                    )  }
                item {
                    CountryBody("https://mainfacts.com/media/images/coats_of_arms/in.png")
                }
                item {  }
            }
        }
    }
}

@Composable
fun CountryHeader(
    countryImage:String,
    navigationUp: () -> Unit
) {
    DetailAppBar(title = "India", pressOnBack = navigationUp)
}
@Composable
fun CountryBody(
imagePath:String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                DefaultBackgroundColor
            )
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        Row() {
            CoilImage(
                modifier = Modifier
                    .height(250.dp)
                    .width(190.dp),
                imageModel = { imagePath },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                    contentDescription = "Alt",
                    colorFilter = null
                ),
                component = rememberImageComponent {
                    +CircularRevealPlugin(
                        duration = 800
                    )
                    +ShimmerPlugin(
                        baseColor = SecondaryFontColor,
                        highlightColor = DefaultBackgroundColor
                    )
                },
            )
            Column {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "India",
                    color = FontColor,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
                CountryInfo(
                    title = stringResource(id = R.string.official),
                    info = "Republic of India"
                )
                CountryInfo(
                    title = stringResource(id = R.string.capital),
                    info = "New Delhi"
                )
                CountryInfo(
                    title = stringResource(id = R.string.population),
                    info = "1380004385"
                )
                CountryInfo(
                    title = stringResource(id = R.string.area),
                    info = "3287590"
                )
                CountryInfo(
                    title = stringResource(id = R.string.latlng),
                    info = "46.11666666,\n" +
                            "14.81666666"
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
            text = "The flag of India is composed of three equal horizontal bands of saffron, white and green. A navy blue wheel with twenty-four spokes — the Ashoka Chakra — is centered in the white band."
        )
    }
}

@Composable
fun CountryInfo(title: String, info: String) {
    Column(modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)) {
        Text(
            text = title,
            color = SecondaryFontColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = info, color = FontColor, fontSize = 16.sp
        )
    }
}

@Composable
fun AboutText(text:String) {
    Text(
        text = text,
        style = MaterialTheme.typography.aboutText
    )
}