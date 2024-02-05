package com.example.countrydemo.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.countrydemo.R
import com.example.countrydemo.data.model.Country


@Composable
fun CountryGrid(
    countries:List<Country>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            items(countries) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    backgroundColor = Color(0xFFEBF7FE),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row {
                        AsyncImage(
                            model = item.flags.png,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp),
                            contentScale = ContentScale.Inside,
                            placeholder = painterResource(id = R.drawable.placeholder)
                        )
                        Column() {
                            CountryInfo(
                                title = stringResource(id = R.string.name),
                                info = item.name.common
                            )
                            CountryInfo(
                                title = stringResource(id = R.string.region),
                                info = item.region
                            )
                        }
                    }
                }
            }
        }
    }
}