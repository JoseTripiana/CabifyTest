package com.tripiana.cabifytest.data.repository.network.response

import com.google.gson.annotations.SerializedName
import com.tripiana.cabifytest.data.entity.DiscountEntity

data class DiscountsResponse(
    @SerializedName("discounts")
    val discounts: List<DiscountEntity>
)