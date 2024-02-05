package com.example.countrydemo.ui.screens.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.countrydemo.ui.theme.aboutText

@Composable
fun AboutText(text:String) {
    Text(
        text = text,
        style = MaterialTheme.typography.aboutText
    )
}