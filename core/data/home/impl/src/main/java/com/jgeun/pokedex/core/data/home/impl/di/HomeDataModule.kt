package com.jgeun.pokedex.core.data.home.impl.di

import com.jgeun.pokedex.core.data.home.api.repository.HomeRepository
import com.jgeun.pokedex.core.data.home.impl.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeDataModule {

    @Binds
    @Singleton
    fun bindsHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository
}