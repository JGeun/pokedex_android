package com.jgeun.pokedex.core.database.api

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jgeun.pokedex.core.database.api.entity.PokemonEntity
import com.jgeun.pokedex.core.database.api.entity.PokemonInfoEntity

@Database(
    entities = [PokemonEntity::class, PokemonInfoEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao

    companion object {
        const val NAME = "Pokedex.db"
    }
}