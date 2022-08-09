package com.tripiana.cabifytest.app.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.tripiana.cabifytest.app.ui.addproduct.strategy.ProductDetailStrategy
import com.tripiana.cabifytest.data.repository.network.ProductRepository
import com.tripiana.cabifytest.domain.model.ProductInfoModel
import com.tripiana.cabifytest.domain.model.ProductModel
import com.tripiana.cabifytest.util.result.toResultLiveData

class MainViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    var productsInfoList = MutableLiveData<List<ProductInfoModel>>()
    var productsInfoEmpty = productsInfoList.map {
        it?.isEmpty()
    }

    val productList = MutableLiveData<List<ProductModel>>(listOf())

    var productsEmpty = productList.map {
        it?.isEmpty()
    }

    val totalPrice = productList.map {
        it?.sumOf { product -> product.getTotalPrice().toDouble() }?.toFloat() ?: 0f
    }

    val totalSaved = productList.map {
        it?.sumOf { product -> product.getTotalSaved().toDouble() }?.toFloat() ?: 0f
    }

    fun initProducts() = toResultLiveData {
        val productsTypeList = productRepository.getProducts()
        val discountsList = productRepository.getDiscounts()

        productsInfoList.value = productsTypeList.map { productType ->
            ProductInfoModel.createProductInfoModel(
                productType,
                discountsList.firstOrNull { it.code == productType.code }
            )
        }
    }

    fun addProduct(product: ProductModel, strategy: ProductDetailStrategy?) {
        val updatedList = productList.value.orEmpty().toMutableList()

        val productIndex = updatedList.indexOfFirst { it.product.id == product.id }

        if (productIndex != -1) {
            strategy?.addProduct(product, productIndex, updatedList)
        } else {
            updatedList.add(product)
        }

        productList.value = updatedList
    }

    fun deleteProduct(item: ProductModel) {
        val updatedList = productList.value.orEmpty().toMutableList()
        val index = updatedList.indexOfFirst { it.product.id == item.product.id }
        if (index != -1) {
            updatedList.removeAt(index)
        }
        productList.value = updatedList
    }

    fun pay(): Pair<Float, Float> {
        val totalAndSaved = Pair(totalPrice.value ?: 0f, totalSaved.value ?: 0f)
        productList.value = listOf()
        return totalAndSaved
    }
}