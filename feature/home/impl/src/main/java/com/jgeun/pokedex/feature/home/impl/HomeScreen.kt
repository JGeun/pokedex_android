package com.jgeun.pokedex.feature.home.impl

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.jgeun.pokedex.core.designsystem.component.PokedexCircularProgress
import com.jgeun.pokedex.core.model.Pokemon

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigateToDetail: (pokemon: Pokemon) -> Unit
) {

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val pokemonList by homeViewModel.pokemonList.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        pokemonList = pokemonList,
        fetchNextPokemonList = homeViewModel::fetchNextPokemonList,
        onNavigateToDetail = onNavigateToDetail
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    pokemonList: List<Pokemon>,
    fetchNextPokemonList: () -> Unit,
    onNavigateToDetail: (pokemon: Pokemon) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val threadHold = 8

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(6.dp),
        ) {
            itemsIndexed(items = pokemonList, key = { _, pokemon -> pokemon.name }) { index, pokemon ->
                if ((index + threadHold) >= pokemonList.size && uiState != HomeUiState.Loading) {
                    fetchNextPokemonList()
                }

                PokemonCard(
                    pokemon = pokemon,
                    onNavigateToDetail = onNavigateToDetail
                )
            }
        }

        if (uiState == HomeUiState.Loading) {
            PokedexCircularProgress()
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    onNavigateToDetail: (pokemon: Pokemon) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable {
                onNavigateToDetail(pokemon)
            },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {

        Log.e("test@@@",  "pokemon.imageUrl: ${pokemon.imageUrl}")

        GlideImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .size(120.dp),
            model = pokemon.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "pokemon_preview",
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp)
                .fillMaxWidth(),
            text = pokemon.name,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}