package com.jgeun.pokedex.feature.detail.impl.di

import com.jgeun.pokedex.core.navigator.DetailNavigator
import com.jgeun.pokedex.feature.detail.impl.navigation.DetailNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DetailNavigatorModule {

    @Binds
    @Singleton
    fun bindsDetailNavigator(
        detailNavigatorImpl: DetailNavigatorImpl
    ) : DetailNavigator
}