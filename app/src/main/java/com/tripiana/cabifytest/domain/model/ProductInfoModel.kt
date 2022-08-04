package com.tripiana.cabifytest.domain.model

data class ProductInfoModel(
    val code: String,
    val name: String,
    val price: Double,
    val discounts: DiscountModel? = null
) {

    companion object {
        fun createProductInfoModel(
            productTypeModel: ProductTypeModel,
            discountModel: DiscountModel?
        ) = ProductInfoModel(
            code = productTypeModel.code,
            name = productTypeModel.name,
            price = productTypeModel.price,
            discounts = discountModel
        )
    }
}