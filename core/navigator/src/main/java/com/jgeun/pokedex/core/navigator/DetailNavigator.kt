package com.jgeun.pokedex.core.navigator

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.jgeun.pokedex.core.model.Pokemon

const val DETAIL_POKEMON = "pokemon"
const val DETAIL_BASE_ROUTE = "detail"
const val DETAIL_ROUTE = "$DETAIL_BASE_ROUTE/{$DETAIL_POKEMON}"

interface DetailNavigator {

    fun detailScreen(builder: NavGraphBuilder)

    fun navigateToDetailScreen(navController: NavController, pokemon: Pokemon, navOptions: NavOptions? = null)
}