package com.jgeun.pokedex.core.model.common

sealed class NetworkResult<out T : Any> {
    data class Success<T : Any>(val data: T) : NetworkResult<T>()
    data class Error<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    data class Exception<T: Any>(val t: Throwable) : NetworkResult<T>()
}

suspend fun <T: Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Success<T>) {
        executable(data)
    }
}

suspend fun <T : Any> NetworkResult<T>.onFailure(
    executable: suspend (code: Int, message: String?) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Error<T>) {
        executable(code, message)
    }
}

suspend fun <T : Any> NetworkResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Exception<T>) {
        executable(t)
    }
}