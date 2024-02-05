package com.example.countrydemo.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.countrydemo.R

@Composable
fun CountryCard (
    selectedCountry: (String) -> Unit,
    imagePath:String,
    commonName:String,
    capital:String,
    status:String,
    timezones: String
    ){
    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(8.dp).height(150.dp),
        shape = RoundedCornerShape(12.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .clickable { selectedCountry(commonName) },

        ) {
            AsyncImage(
                model = imagePath,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(.2f),
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.placeholder)

            )
            Column(modifier = Modifier.weight(.8f)) {
                Text(
                    text = commonName,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "${stringResource(id = R.string.capital)}: $capital",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal

                )
                Text(
                    text = "${stringResource(id = R.string.status)}: $status",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal

                )
                Text(
                    text = "${stringResource(id = R.string.timezone)}: $timezones",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal

                )
            }
        }
    }
}