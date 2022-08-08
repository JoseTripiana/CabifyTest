package com.tripiana.cabifytest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiscountModel(
    val code: String,
    val hasTwoForOneDisccount: Boolean,
    val bulkDiscounts: List<BulkDiscountModel>
): Parcelable