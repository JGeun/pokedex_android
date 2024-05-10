package com.jgeun.pokedex.feature.detail.impl.di

import com.jgeun.pokedex.feature.detail.api.GetPokemonInfoUseCase
import com.jgeun.pokedex.feature.detail.impl.usecase.GetPokemonInfoUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DetailUseCaseModule {

    @Binds
    @Singleton
    fun bindsGetPokemonInfoUseCase(
        getPokemonInfoUseCaseImpl: GetPokemonInfoUseCaseImpl
    ) : GetPokemonInfoUseCase
}