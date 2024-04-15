package com.jgeun.pokedex.core.networkimpl.adapter

import com.jgeun.pokedex.core.model.common.NetworkResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class NetworkResultCallAdapter(
    private val resultType: Type
) : CallAdapter<Type, Call<NetworkResult<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>) = NetworkResultCall(call)
}