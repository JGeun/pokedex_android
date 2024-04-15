package com.jgeun.pokedex.core.networkimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jgeun.pokedex.core.networkimpl.adapter.NetworkResultCallAdapterFactory
import com.jgeun.pokedex.test.MainCoroutinesRule
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.nio.charset.StandardCharsets

@RunWith(JUnit4::class)
abstract class ApiAbstract<T> {

	@Rule
	@JvmField
	val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

	@get:Rule
	val coroutinesRule = MainCoroutinesRule()

	lateinit var mockWebServer: MockWebServer

	@Before
	fun mockServer() {
		mockWebServer = MockWebServer()
		mockWebServer.start()
	}

	@After
	fun stopServer() {
		mockWebServer.shutdown()
	}

	fun enqueueResponse(fileName: String) {
		enqueueResponse(fileName, emptyMap())
	}

	private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
		val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
		val source = inputStream.source().buffer()
		val mockResponse = MockResponse()
		for((key, value) in headers) {
			mockResponse.addHeader(key, value)
		}
		mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
	}


	fun createService(clazz: Class<T>): T {
		val json = Json { ignoreUnknownKeys = true }

		return Retrofit.Builder()
			.baseUrl(mockWebServer.url("/"))
			.addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
			.addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
			.build()
			.create(clazz)
	}

	fun createService2(clazz: Class<T>): T {
		val json = Json { ignoreUnknownKeys = true }

		return Retrofit.Builder()
			.baseUrl(mockWebServer.url("https://pokeapi.co/api/v2/"))
			.addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
			.addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
			.build()
			.create(clazz)
	}
}