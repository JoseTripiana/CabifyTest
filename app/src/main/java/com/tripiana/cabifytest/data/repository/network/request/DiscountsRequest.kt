package com.tripiana.cabifytest.data.repository.network.request

import com.google.gson.annotations.SerializedName
import com.tripiana.cabifytest.data.entity.DiscountEntity

data class DiscountsRequest(
    @SerializedName("discounts")
    val discounts: List<DiscountEntity>
)