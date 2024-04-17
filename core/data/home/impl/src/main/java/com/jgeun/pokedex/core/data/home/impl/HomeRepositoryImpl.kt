package com.jgeun.pokedex.core.data.home.impl

import com.jgeun.pokedex.core.data.home.api.repository.HomeRepository
import com.jgeun.pokedex.core.database.api.PokemonDao
import com.jgeun.pokedex.core.database.api.entity.PokemonEntity
import com.jgeun.pokedex.core.database.api.entity.mapper.asDomain
import com.jgeun.pokedex.core.model.Pokemon
import com.jgeun.pokedex.core.model.common.NetworkResult
import com.jgeun.pokedex.core.model.common.onException
import com.jgeun.pokedex.core.model.common.onFailure
import com.jgeun.pokedex.core.model.common.onSuccess
import com.jgeun.pokedex.core.network.Dispatcher
import com.jgeun.pokedex.core.network.PokedexDispatchers
import com.jgeun.pokedex.core.network.api.PokedexClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonDao: PokemonDao,
    @Dispatcher(PokedexDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : HomeRepository {

    override fun fetchPokemonList(
        page: Int,
    ) = flow {
        val pokemonList = pokemonDao.getPokemonList(page).asDomain()
        if (pokemonList.isEmpty()) {
            val response = pokedexClient.fetchPokemonList(page = page)
            response.onSuccess { data ->
                val pokemonEntityList = data.results.map {
                    PokemonEntity(page = page, name = it.name, url = it.url)
                }
                pokemonDao.insertPokemonList(pokemonEntityList)
                emit(NetworkResult.Success(pokemonDao.getAllPokemonList(page).asDomain()))
            }.onFailure { code, message ->
                emit(NetworkResult.Error<List<Pokemon>>(code, message))
            }.onException { throwable ->
                emit(NetworkResult.Exception<List<Pokemon>>(throwable))
            }
        } else {
            emit(NetworkResult.Success(pokemonDao.getAllPokemonList(page).asDomain()))
        }
    }.flowOn(ioDispatcher)

}