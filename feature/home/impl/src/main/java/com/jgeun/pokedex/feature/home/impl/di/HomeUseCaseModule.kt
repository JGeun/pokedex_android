package com.jgeun.pokedex.feature.home.impl.di

import com.jgeun.pokedex.feature.home.api.usecase.GetPokemonListUseCase
import com.jgeun.pokedex.feature.home.impl.usecase.GetPokemonListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeUseCaseModule {

    @Binds
    @Singleton
    fun bindsGetPokemonListUseCase(
        getPokemonListUseCaseImpl: GetPokemonListUseCaseImpl
    ) : GetPokemonListUseCase
}