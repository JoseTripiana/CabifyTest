package com.tripiana.cabifytest.app.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tripiana.cabifytest.data.repository.network.ProductRepository
import com.tripiana.cabifytest.domain.model.DiscountModel
import com.tripiana.cabifytest.domain.model.ProductTypeModel
import com.tripiana.cabifytest.util.result.toResultLiveData

class MainViewModel(
    private val productRepository: ProductRepository
): ViewModel() {

    val productsTypeList = MutableLiveData<List<ProductTypeModel>>()
    val discountsList = MutableLiveData<List<DiscountModel>>()

    fun getProducts() = toResultLiveData {
        productRepository.getProducts().let {
            productsTypeList.value = it
            it
        }
    }

    fun getDiscounts() = toResultLiveData {
        productRepository.getDiscounts().let {
            discountsList.value = it
            it
        }
    }
}