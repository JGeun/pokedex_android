package com.jgeun.pokedex.feature.detail.api

import com.jgeun.pokedex.core.model.PokemonInfo
import com.jgeun.pokedex.core.model.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GetPokemonInfoUseCase {
    operator fun invoke(name: String): Flow<NetworkResult<PokemonInfo>>
}