package com.jgeun.pokedex.core.network.api

import com.jgeun.pokedex.core.model.common.NetworkResult
import com.jgeun.pokedex.core.network.model.dto.PokemonInfoDto
import com.jgeun.pokedex.core.network.model.response.PokemonResponse
import javax.inject.Inject

class PokedexClient @Inject constructor(
    private val pokedexService: PokedexService,
) {

    suspend fun fetchPokemonList(page: Int): NetworkResult<PokemonResponse> =
        pokedexService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE,
        )

    suspend fun fetchPokemonInfo(name: String): NetworkResult<PokemonInfoDto> =
        pokedexService.fetchPokemonInfo(
            name = name,
        )

    companion object {
        private const val PAGING_SIZE = 20
    }
}
