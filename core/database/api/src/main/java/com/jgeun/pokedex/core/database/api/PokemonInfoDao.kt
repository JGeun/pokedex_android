package com.jgeun.pokedex.core.database.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jgeun.pokedex.core.database.api.entity.PokemonInfoEntity

@Dao
interface PokemonInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfo(pokemonInfo: PokemonInfoEntity)

    @Query("SELECT * FROM PokemonInfoEntity WHERE name = :name_")
    suspend fun getPokemonInfo(name_: String): PokemonInfoEntity?
}
