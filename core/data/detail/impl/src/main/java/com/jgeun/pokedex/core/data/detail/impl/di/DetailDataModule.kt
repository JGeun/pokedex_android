package com.jgeun.pokedex.core.data.detail.impl.di

import com.jgeun.pokedex.core.data.detail.api.DetailRepository
import com.jgeun.pokedex.core.data.detail.impl.DetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DetailDataModule {

    @Binds
    @Singleton
    fun bindsDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl
    ) : DetailRepository
}