package com.jgeun.pokedex.core.data.home.api.repository

import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.core.model.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun fetchPokemonList(
        page: Int,
    ): Flow<NetworkResult<List<Pokemon>>>
}