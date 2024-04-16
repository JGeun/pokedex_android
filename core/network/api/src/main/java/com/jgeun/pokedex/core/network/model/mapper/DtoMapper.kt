package com.jgeun.pokedex.core.network.model.mapper

interface DtoMapper<Domain, Dto> {

    fun asDto(domain: Domain): Dto

    fun asDomain(dto: Dto): Domain
}