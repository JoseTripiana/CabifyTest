package com.tripiana.cabifytest.app.di

import com.tripiana.cabifytest.data.repository.network.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single{
        ProductRepository(
            get(),
            get(),
            get(),
            get()
        )
    }
}