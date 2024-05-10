package com.jgeun.pokedex.core.networkimpl.di

import com.jgeun.pokedex.core.network.api.PokedexClient
import com.jgeun.pokedex.core.network.api.PokedexService
import com.jgeun.pokedex.core.networkimpl.BuildConfig
import com.jgeun.pokedex.core.networkimpl.adapter.NetworkResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

	@Provides
	@Singleton
	fun providesOkHttpClient(): OkHttpClient {
		return OkHttpClient.Builder()
			.connectTimeout(15, TimeUnit.SECONDS)
			.writeTimeout(20, TimeUnit.SECONDS)
			.readTimeout(15, TimeUnit.SECONDS)
			.addInterceptor(
				HttpLoggingInterceptor().apply {
					level = if (BuildConfig.DEBUG) {
						HttpLoggingInterceptor.Level.BODY
					} else {
						HttpLoggingInterceptor.Level.NONE
					}
				}
			)
			.build()
	}

	@Provides
	@Singleton
	fun providesPokedexRetrofit(okHttpClient: OkHttpClient): Retrofit {
		val json = Json {
			isLenient = true
			ignoreUnknownKeys = true
		}
		return Retrofit.Builder()
			.baseUrl("https://pokeapi.co/api/v2/")
			.addConverterFactory(
				json.asConverterFactory("application/json; charset=UTF8".toMediaType())
			)
			.addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
			.client(okHttpClient)
			.build()
	}

	@Provides
	@Singleton
	fun providesPokedexService(retrofit: Retrofit): PokedexService {
		return retrofit.create(PokedexService::class.java)
	}

	@Provides
	@Singleton
	fun providesPokedexClient(pokedexService: PokedexService): PokedexClient {
		return PokedexClient(pokedexService)
	}
}