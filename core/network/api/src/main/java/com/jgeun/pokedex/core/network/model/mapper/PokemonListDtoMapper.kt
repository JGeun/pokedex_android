package com.jgeun.pokedex.core.network.model.mapper

import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.core.network.model.dto.PokemonDto

object PokemonListDtoMapper : DtoMapper<List<Pokemon>, List<PokemonDto>> {

  override fun asDto(domain: List<Pokemon>): List<PokemonDto> {
    return domain.map { pokemon ->
      PokemonDto(
        page = pokemon.page,
        name = pokemon.name,
        url = pokemon.url,
      )
    }
  }

  override fun asDomain(dto: List<PokemonDto>): List<Pokemon> {
    return dto.map { pokemonDto ->
      Pokemon(
        page = pokemonDto.page,
        nameField = pokemonDto.name,
        url = pokemonDto.url,
      )
    }
  }
}

fun List<Pokemon>.asEntity(): List<PokemonDto> {
  return PokemonListDtoMapper.asDto(this)
}

fun List<PokemonDto>?.asDomain(): List<Pokemon> {
  return PokemonListDtoMapper.asDomain(this.orEmpty())
}
