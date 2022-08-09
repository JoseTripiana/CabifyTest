package com.tripiana.cabifytest.app.ui.addproduct.strategy

import android.content.Context
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.domain.model.ProductModel
import kotlinx.parcelize.Parcelize

@Parcelize
class AddProductStrategy : ProductDetailStrategy {
    override fun getProduct(): ProductModel? = null

    override fun canSelectProduct(): Boolean = true

    override fun getButtonSendText(context: Context): String {
        return context.getString(R.string.add_product)
    }

    override fun addProduct(product: ProductModel, productIndex: Int, list: MutableList<ProductModel>) {
        val currentProduct = list[productIndex]
        val updatedProduct = ProductModel(product.product, product.units + currentProduct.units)

        list[productIndex] = updatedProduct
    }
}