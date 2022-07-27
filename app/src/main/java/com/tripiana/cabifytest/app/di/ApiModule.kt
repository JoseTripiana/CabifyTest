package com.tripiana.cabifytest.app.di

import com.tripiana.cabifytest.data.repository.network.api.ProductApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    factory { get<Retrofit>().create(ProductApi::class.java) }
}