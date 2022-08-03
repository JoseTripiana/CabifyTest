package com.tripiana.cabifytest.app.di

import com.tripiana.cabifytest.data.mapper.BulkDiscountMapper
import com.tripiana.cabifytest.data.mapper.DiscountMapper
import com.tripiana.cabifytest.data.mapper.ProductTypeMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val mapperModule = module {
    factoryOf(::BulkDiscountMapper)
    factoryOf(::ProductTypeMapper)

    factory {
        DiscountMapper(get())
    }
}