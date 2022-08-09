package com.tripiana.cabifytest.data.repository.network

import com.tripiana.cabifytest.data.mapper.DiscountMapper
import com.tripiana.cabifytest.data.mapper.ProductTypeMapper
import com.tripiana.cabifytest.data.repository.network.api.ProductApi
import com.tripiana.cabifytest.data.repository.network.config.BaseRetrofitCall
import com.tripiana.cabifytest.domain.model.DiscountModel
import com.tripiana.cabifytest.domain.model.ProductTypeModel

class ProductRepository(
    private val baseRetrofitCall: BaseRetrofitCall,
    private val productApi: ProductApi,
    private val productTypeMapper: ProductTypeMapper,
    private val discountMapper: DiscountMapper
) {

    suspend fun getProducts(): List<ProductTypeModel> =
        baseRetrofitCall.makeCall {
            productApi.getProducts()
        }.let {
            productTypeMapper.toModelList(it.products.toMutableList())
        }

    suspend fun getDiscounts(): List<DiscountModel> =
        baseRetrofitCall.makeCall {
            productApi.getDiscounts()
        }.let {
            discountMapper.toModelList(it.discounts.toMutableList())
        }

}