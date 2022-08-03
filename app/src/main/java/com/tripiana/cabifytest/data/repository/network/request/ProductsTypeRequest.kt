package com.tripiana.cabifytest.data.repository.network.request

import com.google.gson.annotations.SerializedName
import com.tripiana.cabifytest.data.entity.ProductTypeEntity

data class ProductsTypeRequest(
    @SerializedName("products")
    val products: List<ProductTypeEntity>
)