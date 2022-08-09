package com.tripiana.cabifytest.app.di

import com.tripiana.cabifytest.data.repository.network.api.ProductApi
import com.tripiana.cabifytest.data.repository.network.config.BaseRetrofitCall
import org.koin.dsl.module

val apiModule = module {
    factory { get<BaseRetrofitCall>().retrofit.create(ProductApi::class.java) }
}