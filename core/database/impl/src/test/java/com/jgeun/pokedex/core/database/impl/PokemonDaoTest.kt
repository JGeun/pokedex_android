package com.jgeun.pokedex.core.database.impl

import com.jgeun.pokedex.core.database.api.PokemonDao
import com.jgeun.pokedex.core.database.api.entity.mapper.asEntity
import com.jgeun.pokedex.test.MockUtil.mockPokemon
import com.jgeun.pokedex.test.MockUtil.mockPokemonList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [26])
class PokemonDaoTest : LocalDatabase() {

    private lateinit var pokemonDao: PokemonDao

    @Before
    fun init() {
        pokemonDao = db.pokemonDao()
    }

    @Test
    fun insertAndLoadPokemonListTest() = runBlocking {
        val mockDataList = mockPokemonList().asEntity()
        pokemonDao.insertPokemonList(mockDataList)

        val loadFromDB = pokemonDao.getPokemonList(page = 0)
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = listOf(mockPokemon()).asEntity()[0]
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))

        assertThat(loadFromDB.size == mockDataList.size, `is`(true))
    }
}