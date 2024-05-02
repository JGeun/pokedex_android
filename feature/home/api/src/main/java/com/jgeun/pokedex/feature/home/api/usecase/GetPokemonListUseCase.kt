package com.jgeun.pokedex.feature.home.api.usecase

import com.jgeun.pokedex.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface GetPokemonListUseCase {

    operator fun invoke(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>>
}