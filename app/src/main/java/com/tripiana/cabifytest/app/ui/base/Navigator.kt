package com.tripiana.cabifytest.app.ui.base

import android.content.Context
import com.tripiana.cabifytest.app.ui.addproduct.ProductDetailActivity
import com.tripiana.cabifytest.app.ui.addproduct.strategy.ProductDetailStrategy
import com.tripiana.cabifytest.domain.model.ProductInfoModel

class Navigator(
    private val context: Context
) {
    fun getAddProductActivityIntent(productInfoList: List<ProductInfoModel>, strategy: ProductDetailStrategy) =
        ProductDetailActivity.getIntent(context, productInfoList, strategy)
}