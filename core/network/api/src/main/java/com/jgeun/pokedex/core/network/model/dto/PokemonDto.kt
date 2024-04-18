package com.jgeun.pokedex.core.network.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
	@SerialName("page") var page: Int = 0,
	@SerialName("name") val name: String,
	@SerialName("url") val url: String
)