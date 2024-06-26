package com.jgeun.pokedex.core.networkimpl.adapter

import com.jgeun.pokedex.core.model.common.NetworkResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class NetworkResultCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        // 먼저 리턴 타입의 로우 타입이 Call인지 확인한다.
        if (getRawType(returnType) != Call::class.java) return null

        // 이후 리턴타입이 제네릭 인자를 가지는지 확인한다. 리턴 타입은 Call<?>가 돼야 한다.
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<Result<Foo>> or Call<Result<out Foo>>"
        }

        // 리턴 타입에서 첫 번째 제네릭 인자를 얻는다.
        val responseType = getParameterUpperBound(0, returnType)

        // 기대한 것처럼 동작하기 위해선 추출한 제네릭 인자가 Result타입이어야 한다.
        if (getRawType(responseType) != NetworkResult::class.java) return null

        // Result 클래스가 제네릭 인자를 가지는지 확인한다. 제네릭 인자로는 응답을 변환할 클래스를 받아야 한다.
        check(responseType is ParameterizedType) {
            "Response must be parameterized as Result<Foo> or Result<out Foo>"
        }

        // 마지막으로 Result의 제네릭 인자를 얻어서 CallAdapter를 생성한다.
        val resultType = getParameterUpperBound(0, responseType)

        return NetworkResultCallAdapter(resultType)
    }

    companion object {
        fun create(): NetworkResultCallAdapterFactory = NetworkResultCallAdapterFactory()
    }
}