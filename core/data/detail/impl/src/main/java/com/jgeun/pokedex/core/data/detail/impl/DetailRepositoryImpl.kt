package com.jgeun.pokedex.core.data.detail.impl

import com.jgeun.pokedex.core.data.detail.api.DetailRepository
import com.jgeun.pokedex.core.database.api.PokemonInfoDao
import com.jgeun.pokedex.core.database.api.entity.mapper.asDomain
import com.jgeun.pokedex.core.database.api.entity.mapper.asEntity
import com.jgeun.pokedex.core.model.PokemonInfo
import com.jgeun.pokedex.core.model.common.NetworkResult
import com.jgeun.pokedex.core.model.common.onException
import com.jgeun.pokedex.core.model.common.onFailure
import com.jgeun.pokedex.core.model.common.onSuccess
import com.jgeun.pokedex.core.network.Dispatcher
import com.jgeun.pokedex.core.network.PokedexDispatchers
import com.jgeun.pokedex.core.network.api.PokedexClient
import com.jgeun.pokedex.core.network.model.mapper.asDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonInfoDao: PokemonInfoDao,
    @Dispatcher(PokedexDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {

    override fun fetchPokemonInfo(name: String) = flow {
        val pokemonInfoEntity = pokemonInfoDao.getPokemonInfo(name)

        if (pokemonInfoEntity == null) {
            val response = pokedexClient.fetchPokemonInfo(name)
            response.onSuccess { data ->
                pokemonInfoDao.insertPokemonInfo(data.asDomain().asEntity())
                emit(NetworkResult.Success(pokemonInfoDao.getPokemonInfo(name)?.asDomain() ?: data.asDomain()))
            }.onFailure { code, message ->
                emit(NetworkResult.Error<PokemonInfo>(code, message))
            }.onException { throwable ->
                emit(NetworkResult.Exception<PokemonInfo>(throwable))
            }
        } else {
            emit(NetworkResult.Success(pokemonInfoEntity.asDomain()))
        }
    }.flowOn(ioDispatcher)
}