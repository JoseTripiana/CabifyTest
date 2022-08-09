package com.tripiana.cabifytest.app.ui.addproduct.strategy

import android.content.Context
import android.os.Parcelable
import com.tripiana.cabifytest.domain.model.ProductModel

interface ProductDetailStrategy : Parcelable {

    fun getProduct(): ProductModel?
    fun canSelectProduct(): Boolean
    fun getButtonSendText(context: Context): String
    fun addProduct(product: ProductModel, productIndex: Int, list: MutableList<ProductModel>)
}