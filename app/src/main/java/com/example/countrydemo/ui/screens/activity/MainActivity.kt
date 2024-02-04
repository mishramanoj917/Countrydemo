package com.example.countrydemo.ui.screens.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.toArgb
import com.example.countrydemo.navigation.AppNavigation
import com.example.countrydemo.ui.screens.mainscreen.MainScreen
import com.example.countrydemo.ui.theme.CountriesCosmeticsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesCosmeticsTheme {
                window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppNavigation()
                }
            }
        }
    }
}
