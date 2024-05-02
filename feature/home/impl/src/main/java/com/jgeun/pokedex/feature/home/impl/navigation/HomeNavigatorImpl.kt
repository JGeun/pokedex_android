package com.jgeun.pokedex.feature.home.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.core.navigator.HOME_ROUTE
import com.jgeun.pokedex.core.navigator.HomeNavigator
import com.jgeun.pokedex.feature.home.impl.HomeRoute
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor(

): HomeNavigator {

    override fun homeScreen(
        builder: NavGraphBuilder,
        onNavigateToDetail: (pokemon: Pokemon) -> Unit
    ) = builder.run {

        composable(
            route = HOME_ROUTE,
        ) {
            HomeRoute(
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }

    override fun NavController.navigateHomeScreen(navOptions: NavOptions?) {
        this.navigate(HOME_ROUTE, navOptions)
    }
}