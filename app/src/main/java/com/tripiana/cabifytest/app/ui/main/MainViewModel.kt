package com.tripiana.cabifytest.app.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.tripiana.cabifytest.data.repository.network.ProductRepository
import com.tripiana.cabifytest.domain.model.ProductInfoModel
import com.tripiana.cabifytest.domain.model.ProductModel
import com.tripiana.cabifytest.util.result.toResultLiveData

class MainViewModel(
    private val productRepository: ProductRepository
): ViewModel() {

    var productsInfoList = MutableLiveData<List<ProductInfoModel>>()
    var productsInfoEmpty = productsInfoList.map {
        it?.isEmpty()
    }

    val productList = MutableLiveData<List<ProductModel>>()

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
}