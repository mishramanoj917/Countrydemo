package com.example.countrydemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.countrydemo.navigation.AppDestinations.COUNTRY_DETAIL_KEY
import com.example.countrydemo.ui.screens.countries.MainScreen
import com.example.countrydemo.ui.screens.details.CountryDetail

private object AppDestinations{
    const val COUNTRY_ROUTE= "countries"
    const val COUNTRY_DETAIL_ROUTE = "details"
    const val COUNTRY_DETAIL_KEY ="name"
}
@Composable
fun AppNavigation(
    startDestination:String = AppDestinations.COUNTRY_ROUTE
) {
    val navController = rememberNavController()
    val action = remember(navController) { AppActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            AppDestinations.COUNTRY_ROUTE
        ) {
            MainScreen(selectedCountry = action.selectedCountry)
        }
        composable(
            "${AppDestinations.COUNTRY_DETAIL_ROUTE}/{$COUNTRY_DETAIL_KEY}",
            arguments = listOf(
                navArgument(COUNTRY_DETAIL_KEY) {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            val args = requireNotNull(backStackEntry.arguments)
            CountryDetail(
                countryName = args.getString(COUNTRY_DETAIL_KEY).toString(),
                navigationUp = action.navigateUp
            )
        }
    }
}

private class AppActions(
    navController: NavHostController
) {

    val selectedCountry: (String) -> Unit = { countryName: String ->
        navController.navigate("${AppDestinations.COUNTRY_DETAIL_ROUTE}/$countryName")
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}