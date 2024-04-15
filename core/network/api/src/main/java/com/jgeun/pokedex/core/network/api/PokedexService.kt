package com.jgeun.pokedex.core.network.api

import com.jgeun.pokedex.core.model.common.NetworkResult
import com.jgeun.pokedex.core.network.model.dto.PokemonInfoDto
import com.jgeun.pokedex.core.network.model.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

	@GET("pokemon")
	suspend fun fetchPokemonList(
		@Query("limit") limit: Int = 20,
		@Query("offset") offset: Int = 0
	): NetworkResult<PokemonResponse>

	@GET("pokemon/{name}")
	suspend fun fetchPokemonInfo(
		@Path("name") name: String
	): NetworkResult<PokemonInfoDto>
}