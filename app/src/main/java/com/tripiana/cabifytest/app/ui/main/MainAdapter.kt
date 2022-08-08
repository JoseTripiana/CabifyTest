package com.tripiana.cabifytest.app.ui.main

import com.tripiana.cabifytest.BR
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.app.ui.base.adapter.BaseDiffAdapter
import com.tripiana.cabifytest.domain.model.ProductModel

class MainAdapter: BaseDiffAdapter<ProductModel>() {

    override fun getLayout(viewType: Int): Int = R.layout.item_product

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.binding?.setVariable(BR.item, data[position])
    }
}