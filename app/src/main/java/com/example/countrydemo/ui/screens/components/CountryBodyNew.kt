package com.example.countrydemo.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.countrydemo.R
import com.example.countrydemo.data.model.Country
import com.example.countrydemo.ui.theme.DefaultBackgroundColor
import com.example.countrydemo.ui.theme.FontColor

@Composable
fun CountryBodyNew(
    country: Country
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                DefaultBackgroundColor
            )
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {

        AsyncImage(
            model = country.coatOfArms.png,
            contentDescription = null,
            modifier = Modifier
                .height(250.dp)
                .width(190.dp),
            contentScale = ContentScale.Inside,
            placeholder = painterResource(id = R.drawable.placeholder)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = country.name.common,
                    color = FontColor,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row {
                Column(modifier = Modifier.weight(.5f)) {

                    CountryInfo(
                        title = stringResource(id = R.string.official),
                        info = country.name.official
                    )
                    CountryInfo(
                        title = stringResource(id = R.string.capital),
                        info = country.capital[0].toString()
                    )
                }
                Column(modifier = Modifier.weight(.5f)) {
                    CountryInfo(
                        title = stringResource(id = R.string.population),
                        info = country.population.toString()
                    )
                    CountryInfo(
                        title = stringResource(id = R.string.status),
                        info = country.status
                    )
                }

            }
            Row {

                Box(modifier = Modifier.weight(.5f)) {
                    CountryInfo(
                        title = stringResource(id = R.string.region),
                        info = country.region,
                    )
                }
                Box(modifier = Modifier.weight(.5f)) {
                    CountryInfo(
                        title = stringResource(id = R.string.timezone),
                        info = country.timezones[0]
                    )
                }

            }
        }
        Row(horizontalArrangement = Arrangement.Start) {
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                text = stringResource(R.string.about),
                color = FontColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(4.dp))
            AsyncImage(
                model = country.flags.png,
                contentDescription = null,
                modifier = Modifier
                    .padding(2.dp)
                    .size(24.dp),
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.placeholder)

            )
            Text(text = "", Modifier.weight(.5f))
        }
        AboutText(
            text = country.flags.alt
        )
    }
}