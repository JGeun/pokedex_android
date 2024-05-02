package com.jgeun.pokedex.feature.home.impl.usecase

import com.jgeun.pokedex.core.data.home.api.repository.HomeRepository
import com.jgeun.pokedex.feature.home.api.usecase.GetPokemonListUseCase
import javax.inject.Inject

class GetPokemonListUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
) : GetPokemonListUseCase {

    override fun invoke(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = repository.fetchPokemonList(
        page = page,
        onStart = onStart,
        onComplete = onComplete,
        onError = onError
    )
}