package com.jgeun.pokedex.feature.detail.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.core.navigator.DETAIL_BASE_ROUTE
import com.jgeun.pokedex.core.navigator.DetailNavigator
import com.jgeun.pokedex.core.navigator.DETAIL_POKEMON
import com.jgeun.pokedex.core.navigator.DETAIL_ROUTE
import com.jgeun.pokedex.core.navigator.navtype.PokemonType
import com.jgeun.pokedex.feature.detail.impl.DetailRouter
import javax.inject.Inject

class DetailNavigatorImpl @Inject constructor(

) : DetailNavigator {
    override fun detailScreen(builder: NavGraphBuilder) = builder.run {
        composable(
            route = DETAIL_ROUTE,
            arguments = listOf(
                navArgument(DETAIL_POKEMON) {
                    type = PokemonType()
                    nullable = false
                }
            )
        ) {
            DetailRouter()
        }
    }

    override fun navigateToDetailScreen(navController: NavController, pokemon: Pokemon, navOptions: NavOptions?) {
        navController.navigate("${DETAIL_BASE_ROUTE}/${PokemonType.encodeToString(pokemon)}", navOptions)
    }
}