package com.jgeun.pokedex.core.data.detail.impl

import app.cash.turbine.test
import com.jgeun.pokedex.core.database.api.PokemonInfoDao
import com.jgeun.pokedex.core.database.api.entity.mapper.asEntity
import com.jgeun.pokedex.core.model.common.NetworkResult
import com.jgeun.pokedex.core.network.api.PokedexClient
import com.jgeun.pokedex.core.network.api.PokedexService
import com.jgeun.pokedex.core.network.model.mapper.asDto
import com.jgeun.pokedex.test.MainCoroutinesRule
import com.jgeun.pokedex.test.MockUtil.mockPokemonInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class DetailRepositoryImplTest {

    private lateinit var repository: DetailRepositoryImpl
    private lateinit var client: PokedexClient
    private val service: PokedexService = mock()
    private val pokemonInfoDao: PokemonInfoDao = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    private val pokemonName = "bulbasaur"

    @Before
    fun setup() {
        client = PokedexClient(service)
        repository = DetailRepositoryImpl(client, pokemonInfoDao, coroutinesRule.testDispatcher)
    }

    @Test
    fun fetchPokemonInfoFromNetworkTest() = runTest {
        val mockData = mockPokemonInfo()
        whenever(pokemonInfoDao.getPokemonInfo(name = pokemonName)).thenReturn(null)
        whenever(service.fetchPokemonInfo(name = pokemonName)).thenReturn(
            NetworkResult.Success(mockData.asDto())
        )

        repository.fetchPokemonInfo(name = pokemonName).first()

        verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name = pokemonName)
        verify(service, atLeastOnce()).fetchPokemonInfo(name = pokemonName)
        verify(pokemonInfoDao, atLeastOnce()).insertPokemonInfo(mockData.asEntity())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun fetchPokemonInfoFromDatabaseTest() = runTest {
        val mockData = mockPokemonInfo()
        whenever(pokemonInfoDao.getPokemonInfo(name = pokemonName)).thenReturn(mockData.asEntity())
        whenever(service.fetchPokemonInfo(name = pokemonName)).thenReturn(
            NetworkResult.Success(mockData.asDto())
        )

        repository.fetchPokemonInfo(name = pokemonName).test(5.toDuration(DurationUnit.SECONDS)) {
            val expectItem = (awaitItem() as NetworkResult.Success).data
            assertEquals(expectItem.id, mockData.id)
            assertEquals(expectItem.name, mockData.name)
            assertEquals(expectItem, mockData)
            awaitComplete()
        }

        verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name = pokemonName)
        verifyNoMoreInteractions(service)
    }
}