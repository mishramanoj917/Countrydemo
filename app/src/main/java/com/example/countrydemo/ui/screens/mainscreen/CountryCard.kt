package com.example.countrydemo.ui.screens.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countrydemo.ui.theme.DefaultBackgroundColor
import com.example.countrydemo.ui.theme.SecondaryFontColor
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Preview
@Composable
fun CountryCard (
    selectedCountry: (String) -> Unit,
    imagePath:String ="https://flagcdn.com/w320/in.png",
    commonName:String ="India",
    capital:String ="New Delhi",
    ){
    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(8.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp).clickable { selectedCountry(commonName) },

        ) {
            CoilImage(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(.2f),
                imageModel = { imagePath },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
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
            Column(modifier = Modifier.weight(.8f)) {
                Text(
                    text = commonName,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = capital,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal

                )
            }
        }
    }

}