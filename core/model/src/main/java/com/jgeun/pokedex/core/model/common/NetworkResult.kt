package com.jgeun.pokedex.core.model.common

sealed class NetworkResult<out T : Any> {
    data class Success<T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val code: Int, val message: String?) : NetworkResult<Nothing>()
    data class Exception(val t: Throwable?) : NetworkResult<Nothing>()
}