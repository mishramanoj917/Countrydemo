package com.example.countrydemo.ui.screens.components.appbar

import android.graphics.drawable.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.countrydemo.ui.theme.Purple500

@Composable
fun HomeAppBar(title: String) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = {
            Text(
                text = "Countries",
                color= Color.White,
                style = MaterialTheme.typography.h6,
            )
        }
    )
}