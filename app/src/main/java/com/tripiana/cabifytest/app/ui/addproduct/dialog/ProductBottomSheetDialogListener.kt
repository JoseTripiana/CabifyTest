package com.tripiana.cabifytest.app.ui.addproduct.dialog

import com.tripiana.cabifytest.app.ui.base.adapter.BaseClickHandler
import com.tripiana.cabifytest.domain.model.ProductInfoModel

class ProductBottomSheetDialogListener(
    val dialog: ProductBottomSheetDialog,
    val listener: (ProductInfoModel) -> Unit
): BaseClickHandler<ProductInfoModel> {
    override fun onItemClick(item: ProductInfoModel) {
        listener(item)
        dialog.dismiss()
    }
}