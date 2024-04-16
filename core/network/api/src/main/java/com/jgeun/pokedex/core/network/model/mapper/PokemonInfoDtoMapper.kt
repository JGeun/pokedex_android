package com.jgeun.pokedex.core.network.model.mapper

import com.jgeun.pokedex.core.model.PokemonInfo
import com.jgeun.pokedex.core.network.model.dto.PokemonInfoDto

object PokemonInfoDtoMapper : DtoMapper<PokemonInfo, PokemonInfoDto> {

    override fun asDto(domain: PokemonInfo): PokemonInfoDto {
        return PokemonInfoDto(
            id = domain.id,
            name = domain.name,
            height = domain.height,
            weight = domain.weight,
            experience = domain.experience,
            types = domain.types.map { type ->
                PokemonInfoDto.TypeResponse(
                     slot = type.slot,
                     type = PokemonInfoDto.Type(type.type.name)
                )
            },
        )
    }

    override fun asDomain(dto: PokemonInfoDto): PokemonInfo {
        return PokemonInfo(
            id = dto.id,
            name = dto.name,
            height = dto.height,
            weight = dto.weight,
            experience = dto.experience,
            types = dto.types.map { type ->
              PokemonInfo.TypeResponse(
                slot = type.slot,
                type = PokemonInfo.Type(type.type.name)
              )
            }
        )
    }
}

fun PokemonInfo.asDto(): PokemonInfoDto {
    return PokemonInfoDtoMapper.asDto(this)
}

fun PokemonInfoDto.asDomain(): PokemonInfo {
    return PokemonInfoDtoMapper.asDomain(this)
}
