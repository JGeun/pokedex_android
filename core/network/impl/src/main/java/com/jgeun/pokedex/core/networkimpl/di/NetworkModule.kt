package com.jgeun.pokedex.core.networkimpl.di

import com.jgeun.pokedex.core.networkimpl.BuildConfig
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
	fun providesPokedexRetrofit(okHttpClient: OkHttpClient): Retrofit =
		Retrofit.Builder()
			.baseUrl(BuildConfig.BASE_URL)
			.addConverterFactory(
				Json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
			.client(okHttpClient)
			.build()
}