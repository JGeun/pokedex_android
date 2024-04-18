package com.jgeun.pokedex.core.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val pokedexDispatchers: PokedexDispatchers)

enum class PokedexDispatchers {
    IO, DEFAULT
}