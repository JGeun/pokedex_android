package com.jgeun.pokedex.core.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun PokedexNavHost(
    navHostController: NavHostController,
    homeNavigator: HomeNavigator,
    detailNavigator: DetailNavigator
) {
    NavHost(
        navController = navHostController,
        startDestination = HOME_ROUTE
    ) {
        homeNavigator.homeScreen(
            builder = this,
            onNavigateToDetail = { pokemon -> detailNavigator.navigateToDetailScreen(navHostController, pokemon) }
        )

        detailNavigator.detailScreen(this)
    }
}