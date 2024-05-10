package com.jgeun.pokedex.feature.detail.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.jgeun.pokedex.core.designsystem.R.*
import com.jgeun.pokedex.core.designsystem.component.PokedexCircularProgress
import com.jgeun.pokedex.core.designsystem.theme.PokedexTheme
import com.jgeun.pokedex.core.designsystem.utils.getPokemonTypeColor
import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.core.model.PokemonInfo


@Composable
fun DetailRouter(
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val detailUiState by detailViewModel.detailUiState.collectAsStateWithLifecycle()

    DetailScreen(
        uiState = detailUiState
    )
}

@Composable
fun DetailScreen(
    uiState: DetailUiState = DetailUiState.Loading
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when (uiState) {
            is DetailUiState.Success -> {
                DetailsHeader(
                    pokemon = uiState.pokemon,
                    pokemonInfo = uiState.pokemonInfo,
                )

                DetailsInfo(pokemonInfo = uiState.pokemonInfo)

                DetailsStatus(pokemonInfo = uiState.pokemonInfo)
            }
            else -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    PokedexCircularProgress()
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun DetailsHeader(
    pokemon: Pokemon?,
    pokemonInfo: PokemonInfo?,
    navigateUp: () -> Unit = {}
) {
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 64.dp,
        bottomEnd = 64.dp,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(290.dp)
            .shadow(elevation = 9.dp, shape = shape),
    ) {
        Row(
            modifier = Modifier.padding(12.dp).statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .clickable { navigateUp() },
                painter = painterResource(id = drawable.ic_arrow),
                tint = PokedexTheme.colors.absoluteWhite,
                contentDescription = null,
            )

            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = pokemon?.name.orEmpty(),
                color = PokedexTheme.colors.absoluteWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .statusBarsPadding(),
            text = pokemonInfo?.getIdString().orEmpty(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        GlideImage(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .size(190.dp),
            model = pokemon?.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }

    Text(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth(),
        text = pokemon?.name.orEmpty(),
        color = PokedexTheme.colors.black,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 36.sp,
    )
}

@Composable
private fun DetailsInfo(pokemonInfo: PokemonInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterHorizontally),
    ) {
        pokemonInfo.types.forEach { typeInfo ->
            Text(
                modifier = Modifier
                    .background(
                        color = getPokemonTypeColor(type = typeInfo.type.name),
                        shape = RoundedCornerShape(64.dp),
                    )
                    .padding(horizontal = 40.dp, vertical = 4.dp),
                text = typeInfo.type.name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = PokedexTheme.colors.absoluteWhite,
                maxLines = 1,
                fontSize = 16.sp,
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        PokemonInfoItem(
            title = pokemonInfo.getWeightString(),
            content = stringResource(id = string.weight),
        )

        PokemonInfoItem(
            title = pokemonInfo.getHeightString(),
            content = stringResource(id = string.height),
        )
    }
}

@Composable
private fun DetailsStatus(
    pokemonInfo: PokemonInfo,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 16.dp),
        text = stringResource(id = string.base_stats),
        textAlign = TextAlign.Center,
        color = PokedexTheme.colors.black,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
    )

    Column {
        pokemonInfo.toPokedexStatusList().forEach { pokemonStatus ->
            PokemonStatusItem(
                modifier = Modifier.padding(bottom = 12.dp),
                pokedexStatus = pokemonStatus,
            )
        }
    }
}