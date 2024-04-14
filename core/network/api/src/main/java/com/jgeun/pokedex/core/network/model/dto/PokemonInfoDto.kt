package com.jgeun.pokedex.core.network.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonInfoDto(
	@SerialName("id") val id: Int,
	@SerialName("name") val name: String,
	@SerialName("height") val height: Int,
	@SerialName("weight") val weight: Int,
	@SerialName("base_experience") val experience: Int,
	@SerialName("types") val types: List<TypeResponse>,
) {
	@Serializable
	data class TypeResponse(
		@SerialName("slot") val slot: Int,
		@SerialName("type") val type: Type
	)

	@Serializable
	data class Type(
		@SerialName("name") val name: String
	)
}