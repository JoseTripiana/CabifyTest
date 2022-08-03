package com.tripiana.cabifytest.data.entity

import com.google.gson.annotations.SerializedName

data class DiscountEntity(
    @SerializedName("code")
    val code: String,
    @SerializedName("hasTwoForOneDisccount")
    val hasTwoForOneDisccount: Boolean,
    @SerializedName("bulkDiscounts")
    val bulkDiscounts: List<BulkDiscountEntity>
)