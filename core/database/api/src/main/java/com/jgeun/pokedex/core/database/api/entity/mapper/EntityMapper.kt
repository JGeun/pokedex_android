package com.jgeun.pokedex.core.database.api.entity.mapper

interface EntityMapper<Domain, Entity> {

    fun asEntity(domain: Domain): Entity

    fun asDomain(entity: Entity): Domain
}