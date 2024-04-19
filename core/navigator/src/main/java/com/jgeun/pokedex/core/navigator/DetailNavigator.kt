package com.jgeun.pokedex.core.navigator

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions

const val DETAIL_ROUTE = "detail"

interface DetailNavigator {

    fun detailScreen(builder: NavGraphBuilder)

    fun navigateToDetailScreen(navController: NavController, navOptions: NavOptions? = null)
}