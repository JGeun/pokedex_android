package com.jgeun.pokedex.core.network.model.dto

import com.jgeun.pokedex.core.model.PokemonInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class PokemonInfoDto(
	@SerialName("id") val id: Int,
	@SerialName("name") val name: String,
	@SerialName("height") val height: Int,
	@SerialName("weight") val weight: Int,
	@SerialName("base_experience") val experience: Int,
	@SerialName("types") val types: List<TypeResponse>,
	val hp: Int = Random.nextInt(PokemonInfo.MAX_HP),
	val attack: Int = Random.nextInt(PokemonInfo.MAX_ATTACK),
	val defense: Int = Random.nextInt(PokemonInfo.MAX_DEFENSE),
	val speed: Int = Random.nextInt(PokemonInfo.MAX_SPEED),
	val exp: Int = Random.nextInt(PokemonInfo.MAX_EXP),
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