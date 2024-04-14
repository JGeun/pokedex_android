package com.jgeun.pokedex.core.network.model.response

import com.jgeun.pokedex.core.network.model.dto.PokemonDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
	@SerialName("count") val count: Int,
	@SerialName("next") val next: String?,
	@SerialName("previous") val previous: String?,
	@SerialName("results") val results: List<PokemonDto>
)