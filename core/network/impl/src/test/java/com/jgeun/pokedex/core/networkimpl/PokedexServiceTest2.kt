package com.jgeun.pokedex.core.networkimpl

import com.jgeun.pokedex.core.network.api.PokedexService
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PokedexServiceTest2 : ApiAbstract<PokedexService>() {

    private lateinit var service: PokedexService

    @Before
    fun initService() {
        service = createService2(PokedexService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchPokemonInfoFromNetworkTest() = runTest {
        val response = service.fetchPokemonInfo("bulbasaur")
        val responseBody = requireNotNull(response.body())

        println(response.body())
        MatcherAssert.assertThat(responseBody.id, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(responseBody.name, CoreMatchers.`is`("bulbasaur"))
        MatcherAssert.assertThat(responseBody.height, CoreMatchers.`is`(7))
        MatcherAssert.assertThat(responseBody.weight, CoreMatchers.`is`(69))
        MatcherAssert.assertThat(responseBody.experience, CoreMatchers.`is`(64))
    }
}