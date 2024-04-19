package com.jgeun.pokedex.feature.home.impl.di

import com.jgeun.pokedex.core.navigator.HomeNavigator
import com.jgeun.pokedex.feature.home.impl.navigation.HomeNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeNavigationModule {

    @Binds
    @Singleton
    fun bindsHomeNavigator(
        homeNavigatorImpl: HomeNavigatorImpl
    ) : HomeNavigator
}