package com.tripiana.cabifytest.app.ui.main

import com.tripiana.cabifytest.app.ui.base.adapter.BaseClickHandler
import com.tripiana.cabifytest.domain.model.ProductModel

interface ProductListHandler : BaseClickHandler<ProductModel> {
    override fun onItemClick(item: ProductModel)
    fun onDeleteClick(item: ProductModel)
}