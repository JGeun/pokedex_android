package com.jgeun.pokedex.core.data.home.impl

import com.jgeun.pokedex.core.database.api.PokemonDao
import com.jgeun.pokedex.core.database.api.entity.mapper.asEntity
import com.jgeun.pokedex.core.model.common.NetworkResult
import com.jgeun.pokedex.core.network.api.PokedexClient
import com.jgeun.pokedex.core.network.api.PokedexService
import com.jgeun.pokedex.core.network.model.mapper.asDomain
import com.jgeun.pokedex.core.network.model.mapper.asDto
import com.jgeun.pokedex.core.network.model.response.PokemonResponse
import com.jgeun.pokedex.test.MainCoroutinesRule
import com.jgeun.pokedex.test.MockUtil.mockPokemonList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class HomeRepositoryImplTest {

    private lateinit var repository: HomeRepositoryImpl
    private lateinit var client: PokedexClient
    private val service: PokedexService = mock()
    private val pokemonDao: PokemonDao = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()
    @Before
    fun setup() {
        client = PokedexClient(service)
        repository = HomeRepositoryImpl(client, pokemonDao, coroutinesRule.testDispatcher)
    }

    @Test
    fun fetchPokemonListFromNetworkTest() = runTest {

        // given
        val mockData =
            PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList().asDto())
        whenever(pokemonDao.getPokemonList(page = 0)).thenReturn(emptyList())
        whenever(pokemonDao.getAllPokemonList(page = 0)).thenReturn(mockData.results.asDomain().asEntity())
        whenever(service.fetchPokemonList()).thenReturn(NetworkResult.Success(mockData))

        // when
        repository.fetchPokemonList(page = 0).first()

        // then
        verify(pokemonDao, atLeastOnce()).getPokemonList(page = 0)
        verify(service, atLeastOnce()).fetchPokemonList()
        verify(pokemonDao, atLeastOnce()).insertPokemonList(mockData.results.asDomain().asEntity())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun fetchPokemonListFromDatabaseTest() = runTest {
        val mockData =
            PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList().asDto())
        whenever(pokemonDao.getPokemonList(page = 0)).thenReturn(mockData.results.asDomain().asEntity())
        whenever(pokemonDao.getAllPokemonList(page = 0)).thenReturn(mockData.results.asDomain().asEntity())

        repository.fetchPokemonList(page = 0).first()

        verify(pokemonDao, atLeastOnce()).getPokemonList(page = 0)
        verify(pokemonDao, atLeastOnce()).getAllPokemonList(page = 0)
    }
}