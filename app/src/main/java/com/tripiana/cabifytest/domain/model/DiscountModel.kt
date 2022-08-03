package com.tripiana.cabifytest.domain.model

data class DiscountModel(
    val code: String,
    val hasTwoForOneDisccount: Boolean,
    val bulkDiscounts: List<BulkDiscountModel>
)