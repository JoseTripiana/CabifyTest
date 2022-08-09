package com.tripiana.cabifytest.data.repository.network.response

import com.google.gson.annotations.SerializedName
import com.tripiana.cabifytest.data.entity.ProductTypeEntity

data class ProductsTypeResponse(
    @SerializedName("products")
    val products: List<ProductTypeEntity>
)