package com.tripiana.cabifytest.data.repository.network.config

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory(
    private val converterFactory: Converter.Factory
) {

    val baseUrl: String
        get() = "https://gist.githubusercontent.com/"

    val timeout: Long
        get() = 30L

    val logLevel: HttpLoggingInterceptor.Level
        get() = HttpLoggingInterceptor.Level.BODY

    fun getRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            readTimeout(timeout, TimeUnit.SECONDS)
            writeTimeout(timeout, TimeUnit.SECONDS)
            connectTimeout(timeout, TimeUnit.SECONDS)

            val loginInterceptor = HttpLoggingInterceptor()
            loginInterceptor.level = logLevel
            addInterceptor(loginInterceptor)

        }.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    companion object {
        fun getFactory(
            converterFactory: Converter.Factory
        ): RetrofitFactory {
            return RetrofitFactory(converterFactory)
        }
    }
}