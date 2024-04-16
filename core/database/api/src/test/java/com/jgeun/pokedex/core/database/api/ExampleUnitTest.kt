package com.jgeun.pokedex.core.database.api

import com.jgeun.pokedex.core.model.PokemonInfo
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        val list = listOf<PokemonInfo.TypeResponse>(
            PokemonInfo.TypeResponse(slot = 1, type = PokemonInfo.Type("aa")),
            PokemonInfo.TypeResponse(slot = 2, type = PokemonInfo.Type("aa")),
            PokemonInfo.TypeResponse(slot = 3, type = PokemonInfo.Type("aa")),
        )

        val encoding = Json.encodeToString(list)
        println("json Encode: $encoding")
        val decode = Json.decodeFromString<List<PokemonInfo.TypeResponse>>(encoding)
        println("decode: $decode")
    }
}