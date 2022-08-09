package com.tripiana.cabifytest.app.ui.addproduct.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tripiana.cabifytest.databinding.DialogProductBottomSheetBinding
import com.tripiana.cabifytest.domain.model.ProductInfoModel

class ProductBottomSheetDialog(
    private val list: List<ProductInfoModel>,
    private val listener: (ProductInfoModel) -> Unit
) : BottomSheetDialogFragment() {

    private val binding by lazy {
        DialogProductBottomSheetBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@ProductBottomSheetDialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productAdapter = ProductDialogAdapter(
            ProductBottomSheetDialogListener(
                this,
                listener
            )
        )
        with(binding.recyclerView) {
            adapter = productAdapter
        }
        productAdapter.update(list)
    }
}