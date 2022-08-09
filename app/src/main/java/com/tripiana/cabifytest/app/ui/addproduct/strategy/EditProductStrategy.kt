package com.tripiana.cabifytest.app.ui.addproduct.strategy

import android.content.Context
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.domain.model.ProductModel
import kotlinx.parcelize.Parcelize

@Parcelize
class EditProductStrategy(
    private val product: ProductModel
) : ProductDetailStrategy {
    override fun getProduct(): ProductModel = product

    override fun canSelectProduct(): Boolean = false

    override fun getButtonSendText(context: Context): String {
        return context.getString(R.string.edit_product)
    }

    override fun addProduct(product: ProductModel, productIndex: Int, list: MutableList<ProductModel>) {
        list[productIndex] = product
    }


}