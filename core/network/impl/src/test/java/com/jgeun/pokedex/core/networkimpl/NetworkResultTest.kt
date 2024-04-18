package com.jgeun.pokedex.core.networkimpl

import com.jgeun.pokedex.core.model.common.NetworkResult
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NetworkResultTest {

    @Test
    fun exception() {
        val exception = Exception("foo")
        val result = NetworkResult.Exception<Any>(exception)
        assertThat(result.t.message, `is`("foo"))
    }

    @Test
    fun success() {
        val result = NetworkResult.Success("foo")
        assertThat(result.data, `is`("foo"))
    }
}