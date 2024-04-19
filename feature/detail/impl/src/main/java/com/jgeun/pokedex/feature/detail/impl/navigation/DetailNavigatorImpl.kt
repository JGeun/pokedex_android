package com.jgeun.pokedex.feature.detail.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.jgeun.pokedex.core.navigator.DETAIL_ROUTE
import com.jgeun.pokedex.core.navigator.DetailNavigator
import com.jgeun.pokedex.feature.detail.impl.DetailRouter
import javax.inject.Inject

class DetailNavigatorImpl @Inject constructor(

) : DetailNavigator {
    override fun detailScreen(builder: NavGraphBuilder) = builder.run {
        composable(
            route = DETAIL_ROUTE,
        ) {
            DetailRouter()
        }
    }

    override fun navigateToDetailScreen(navController: NavController, navOptions: NavOptions?) {
        navController.navigate(DETAIL_ROUTE, navOptions)
    }
}