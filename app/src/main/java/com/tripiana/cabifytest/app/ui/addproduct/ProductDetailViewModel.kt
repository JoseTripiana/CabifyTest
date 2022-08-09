package com.tripiana.cabifytest.app.ui.addproduct

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.tripiana.cabifytest.domain.model.ProductInfoModel

class ProductDetailViewModel : ViewModel() {

    val productInfoModelList = mutableListOf<ProductInfoModel>()

    val productSelected = MutableLiveData<ProductInfoModel>()

    val units = MutableLiveData<Int>()

    val hasTwoForOne = productSelected.map {
        it.discounts?.hasTwoForOneDisccount == true
    }

    val pricePerUnit = MediatorLiveData<Float>().apply {
        var currentProduct: ProductInfoModel? = null
        var currentUnits: Int? = null
        var hasTwoForOne: Boolean? = null
        addSource(productSelected) {
            currentProduct = it
            postValue(getPricePerUnit(currentProduct, currentUnits, hasTwoForOne))
        }

        addSource(units) {
            currentUnits = it
            postValue(getPricePerUnit(currentProduct, currentUnits, hasTwoForOne))
        }

        addSource(this@ProductDetailViewModel.hasTwoForOne) {
            hasTwoForOne = it
            postValue(getPricePerUnit(currentProduct, currentUnits, hasTwoForOne))
        }
    }

    val total = MediatorLiveData<Float>().apply {
        var currentUnits: Int? = null
        var pricePerUnit: Float? = null

        addSource(units) {
            currentUnits = it
            postValue(pricePerUnit?.times(it.toFloat()) ?: 0f)
        }

        addSource(this@ProductDetailViewModel.pricePerUnit) {
            pricePerUnit = it
            postValue(currentUnits?.toFloat()?.times(it) ?: 0f)
        }
    }

    val saved = MediatorLiveData<Float>().apply {
        var currentProduct: ProductInfoModel? = null
        var currentUnits: Int? = null
        var pricePerUnit: Float? = null

        addSource(productSelected) {
            currentProduct = it
            postValue(getSaved(currentProduct, currentUnits, pricePerUnit))
        }
        addSource(units) {
            currentUnits = it
            postValue(getSaved(currentProduct, currentUnits, pricePerUnit))
        }

        addSource(this@ProductDetailViewModel.pricePerUnit) {
            pricePerUnit = it
            postValue(getSaved(currentProduct, currentUnits, pricePerUnit))
        }
    }

    private fun getPricePerUnit(currentProduct: ProductInfoModel?, currentUnits: Int?, hasTwoForOne: Boolean?): Float {
        return if (currentUnits != null && currentUnits > 0) {
            val twoForOneTimes = if (hasTwoForOne == true) (currentUnits / 2) else 0
            val newPricePerUnit = currentProduct?.discounts?.bulkDiscounts?.maxByOrNull { bulkDiscount -> bulkDiscount.units < currentUnits }?.price ?: currentProduct?.price ?: 0f
            ((currentUnits - twoForOneTimes) * newPricePerUnit) / currentUnits
        } else
            0f
    }

    private fun getSaved(currentProduct: ProductInfoModel?, currentUnits: Int?, pricePerUnit: Float?): Float {
        return if (currentProduct != null && currentUnits != null && pricePerUnit != null) {
            (currentProduct.price * currentUnits) - (currentUnits * pricePerUnit)
        } else
            0f
    }

}