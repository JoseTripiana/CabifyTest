package com.tripiana.cabifytest.domain.model

import android.os.Parcelable
import com.tripiana.cabifytest.app.ui.base.adapter.Idable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductInfoModel(
    override val id: String,
    val name: String,
    val price: Float,
    val discounts: DiscountModel? = null
) : Parcelable, Idable {

    companion object {
        fun createProductInfoModel(
            productTypeModel: ProductTypeModel,
            discountModel: DiscountModel?
        ) = ProductInfoModel(
            id = productTypeModel.code,
            name = productTypeModel.name,
            price = productTypeModel.price,
            discounts = discountModel
        )
    }
}