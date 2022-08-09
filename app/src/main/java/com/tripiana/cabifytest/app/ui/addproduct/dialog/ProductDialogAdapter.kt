package com.tripiana.cabifytest.app.ui.addproduct.dialog

import com.tripiana.cabifytest.BR
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.app.ui.base.adapter.BaseDiffAdapter
import com.tripiana.cabifytest.domain.model.ProductInfoModel

class ProductDialogAdapter(
    private val handler: ProductBottomSheetDialogListener
) : BaseDiffAdapter<ProductInfoModel>() {
    override fun getLayout(viewType: Int): Int = R.layout.item_product_dialog

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.binding?.setVariable(BR.item, data[position])
        holder.binding?.setVariable(BR.handler, handler)
    }
}