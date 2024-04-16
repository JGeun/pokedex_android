package com.jgeun.pokedex.core.database.api

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.jgeun.pokedex.core.model.PokemonInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class TypeResponseConverter {

    @TypeConverter
    fun fromString(value: String): List<PokemonInfo.TypeResponse> {
        val json = Json { isLenient = true }
        return json.decodeFromString<List<PokemonInfo.TypeResponse>>(value)
    }

    @TypeConverter
    fun fromInfoTypeList(typeResponseList: List<PokemonInfo.TypeResponse>?): String {
        val json = Json { isLenient = true }
        return json.encodeToString(typeResponseList)
    }
}