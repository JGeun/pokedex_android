package com.jgeun.pokedex.core.networkimpl.di

import androidx.core.view.WindowInsetsAnimationCompat.Callback.DispatchMode
import com.jgeun.pokedex.core.networkimpl.Dispatcher
import com.jgeun.pokedex.core.networkimpl.PokedexDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

    @Provides
    @Dispatcher(PokedexDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(PokedexDispatchers.DEFAULT)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}