package com.jgeun.pokedex.core.data.detail.api

import com.jgeun.pokedex.core.model.PokemonInfo
import com.jgeun.pokedex.core.model.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    fun fetchPokemonInfo(
        name: String
    ): Flow<NetworkResult<PokemonInfo>>
}