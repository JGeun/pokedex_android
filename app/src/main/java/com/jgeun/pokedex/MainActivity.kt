package com.jgeun.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jgeun.pokedex.core.navigator.DetailNavigator
import com.jgeun.pokedex.core.navigator.HomeNavigator
import com.jgeun.pokedex.core.navigator.PokedexNavHost
import com.jgeun.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@Inject lateinit var homeNavigator: HomeNavigator
	@Inject lateinit var detailNavigator: DetailNavigator

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val navController = rememberNavController()

			PokedexTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
					PokedexNavHost(
						navHostController = navController,
						homeNavigator = homeNavigator,
						detailNavigator = detailNavigator
					)
				}
			}
		}
	}
}