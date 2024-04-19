package com.jgeun.pokedex.core.data.home.api.repository

import com.jgeun.pokedex.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>>
}