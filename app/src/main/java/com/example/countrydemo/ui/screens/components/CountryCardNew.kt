package com.example.countrydemo.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.countrydemo.R

@Composable
fun CountryCardNew(
    selectedCountry: (String) -> Unit,
    imagePath: String,
    commonName: String,
    capital: String,
    status: String,
    timezones: String
) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { selectedCountry(commonName) },

                ) {

                Column(modifier = Modifier.weight(8f)) {

                    Row {
                        Text(
                            //modifier = Modifier.weight(1f),
                            text = "${commonName.split(" ").first()} ",
                            style = MaterialTheme.typography.h6,
                            softWrap = true,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis

                        )
                        if (status == "officially-assigned") {

                            Image(
                                imageVector = Icons.Rounded.CheckCircle,
                                colorFilter = ColorFilter.tint(Color.Green),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(top = 2.dp)
                            )

                        } else {
                            Text(text = "")
                        }

                        Text(
                            text = " ($timezones)", fontSize = 12.sp, color = Color.Blue

                        )
                    }

                    Text(
                        text = "${stringResource(id = R.string.capital)}: $capital  ",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal

                    )

                }
                AsyncImage(
                    model = imagePath,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(2.dp)
                        .size(40.dp)
                        .weight(2f),
                    contentScale = ContentScale.Inside,
                    placeholder = painterResource(id = R.drawable.placeholder)

                )
            }
        }
    }
}