package com.jgeun.pokedex.core.database.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jgeun.pokedex.core.database.api.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity WHERE page = :page")
    suspend fun getPokemonList(page: Int): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity WHERE page <= :page")
    suspend fun getAllPokemonList(page: Int): List<PokemonEntity>
}