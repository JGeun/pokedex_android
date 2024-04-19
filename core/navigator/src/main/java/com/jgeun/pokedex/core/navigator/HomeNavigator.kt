package com.jgeun.pokedex.core.navigator

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions


const val HOME_ROUTE = "home"

interface HomeNavigator {

    fun homeScreen(
        builder: NavGraphBuilder,
        onNavigateToDetail: () -> Unit
    )

    fun NavController.navigateHomeScreen(navOptions: NavOptions? = null)
}