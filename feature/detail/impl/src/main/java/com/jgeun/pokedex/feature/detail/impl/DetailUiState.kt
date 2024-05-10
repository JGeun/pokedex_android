package com.jgeun.pokedex.feature.detail.impl

import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.core.model.PokemonInfo

sealed interface DetailUiState {

    data object Loading : DetailUiState
    data class Success(
        val pokemon: Pokemon,
        val pokemonInfo: PokemonInfo
    ) : DetailUiState
    data class Failure(val message: String?) : DetailUiState
}
