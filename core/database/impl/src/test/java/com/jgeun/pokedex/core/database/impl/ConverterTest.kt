package com.jgeun.pokedex.core.database.impl

import com.jgeun.pokedex.core.database.api.TypeResponseConverter
import com.jgeun.pokedex.core.model.PokemonInfo
import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterTest {

    private val jsonString = """[{"slot":1,"type":{"name":"name"}}]"""
    private val typeResponseList = listOf(PokemonInfo.TypeResponse(slot = 1, type = PokemonInfo.Type(name = "name")))

    @Test
    fun test_type_response_converter_to_string() {
        assertEquals(
            typeResponseList,
            TypeResponseConverter().fromString(jsonString)
        )
    }

    @Test
    fun test_string_to_type_response_list() {
        assertEquals(
            jsonString,
            TypeResponseConverter().fromInfoTypeList(typeResponseList)
        )
    }
}