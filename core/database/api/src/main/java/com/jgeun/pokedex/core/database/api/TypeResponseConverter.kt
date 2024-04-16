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
        return Json.decodeFromString<List<PokemonInfo.TypeResponse>>(value)
    }

    @TypeConverter
    fun fromInfoType(typeResponseList: List<PokemonInfo.TypeResponse>?): String {
        return Json.encodeToString(typeResponseList)
    }
}