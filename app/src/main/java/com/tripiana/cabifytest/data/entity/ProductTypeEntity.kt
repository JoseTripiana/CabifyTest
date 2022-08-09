package com.tripiana.cabifytest.data.entity

import com.google.gson.annotations.SerializedName

data class ProductTypeEntity(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Float
)