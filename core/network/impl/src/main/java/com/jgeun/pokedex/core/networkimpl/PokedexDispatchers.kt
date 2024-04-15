package com.jgeun.pokedex.core.networkimpl

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val pokedexDispatchers: PokedexDispatchers)

enum class PokedexDispatchers {
    IO, DEFAULT
}