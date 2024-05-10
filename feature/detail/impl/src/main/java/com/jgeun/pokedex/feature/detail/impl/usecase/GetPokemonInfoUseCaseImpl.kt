package com.jgeun.pokedex.feature.detail.impl.usecase

import com.jgeun.pokedex.core.data.detail.api.DetailRepository
import com.jgeun.pokedex.feature.detail.api.GetPokemonInfoUseCase
import javax.inject.Inject

class GetPokemonInfoUseCaseImpl @Inject constructor(
    private val detailRepository: DetailRepository
) : GetPokemonInfoUseCase {

    override fun invoke(
        name: String
    ) = detailRepository.fetchPokemonInfo(
        name = name
    )
}