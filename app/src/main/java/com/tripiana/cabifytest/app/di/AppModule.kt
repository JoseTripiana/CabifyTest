package com.tripiana.cabifytest.app.di

import com.google.gson.GsonBuilder
import com.tripiana.cabifytest.data.repository.network.config.BaseRetrofitCall
import com.tripiana.cabifytest.data.repository.network.config.RetrofitFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        GsonBuilder().create()
    }

    single {
       RetrofitFactory.getFactory(
           converterFactory = GsonConverterFactory.create(get())
       ).getRetrofit()
    }

    single {
        BaseRetrofitCall(get())
    }

}