package com.jgeun.pokedex.core.database.impl.di

import android.content.Context
import androidx.room.Room
import com.jgeun.pokedex.core.database.api.PokedexDatabase
import com.jgeun.pokedex.core.database.api.PokemonDao
import com.jgeun.pokedex.core.database.api.PokemonInfoDao
import com.jgeun.pokedex.core.database.api.TypeResponseConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    fun providesPokedexDatabase(
        @ApplicationContext context: Context,
        typeResponseConverter: TypeResponseConverter
    ): PokedexDatabase {
        return Room
            .databaseBuilder(context, PokedexDatabase::class.java, PokedexDatabase.NAME)
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun providesPokemonDao(pokedexDatabase: PokedexDatabase): PokemonDao {
        return pokedexDatabase.pokemonDao()
    }

    @Provides
    @Singleton
    fun providesPokemonInfoDao(pokedexDatabase: PokedexDatabase): PokemonInfoDao {
        return pokedexDatabase.pokemonInfoDao()
    }

    @Provides
    @Singleton
    fun providesTypeResponseConverter(): TypeResponseConverter {
        return TypeResponseConverter()
    }
}