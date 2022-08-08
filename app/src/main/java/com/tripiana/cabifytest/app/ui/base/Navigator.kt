package com.tripiana.cabifytest.app.ui.base

import android.content.Context
import com.tripiana.cabifytest.app.ui.addproduct.AddProductActivity
import com.tripiana.cabifytest.domain.model.ProductInfoModel

class Navigator(
    private val context: Context
) {
    fun getAddProductActivityIntent(productInfoList: List<ProductInfoModel>) = AddProductActivity.getIntent(context, productInfoList)
}