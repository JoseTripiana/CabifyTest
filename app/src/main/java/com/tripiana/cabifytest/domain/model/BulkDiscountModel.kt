package com.tripiana.cabifytest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BulkDiscountModel(
    val units: Int,
    val price: Float
) : Parcelable