package com.tripiana.cabifytest.app.ui.addproduct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.app.ui.addproduct.dialog.ProductBottomSheetDialog
import com.tripiana.cabifytest.app.ui.base.BaseActivity
import com.tripiana.cabifytest.app.ui.base.adapter.BaseClickHandler
import com.tripiana.cabifytest.app.ui.main.MainActivity.Companion.PRODUCT_PARAM
import com.tripiana.cabifytest.databinding.ActivityAddProductBinding
import com.tripiana.cabifytest.domain.model.ProductInfoModel
import com.tripiana.cabifytest.domain.model.ProductModel
import com.tripiana.cabifytest.domain.model.ProductTypeModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProductActivity: BaseActivity() {


    private val binding: ActivityAddProductBinding by lazy {
        ActivityAddProductBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@AddProductActivity
            productSelected = viewModel.productSelected
            units = viewModel.units
            hasTwoForOne = viewModel.hasTwoForOne
            pricePerUnit = viewModel.pricePerUnit
            total = viewModel.total
            saved = viewModel.saved
            handler = ClickHandler()
        }
    }

    private val viewModel: AddProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        intent.extras?.getParcelableArrayList<ProductInfoModel>(DISCOUNTS_PARAM)?.let {
            viewModel.productInfoModelList.addAll(it)
        }

    }

    inner class ClickHandler {
        fun onProductClick(){
            ProductBottomSheetDialog(
                viewModel.productInfoModelList
            ){
                viewModel.productSelected.value = it
                viewModel.units.value = 0
            }.show(supportFragmentManager, null)
        }

        fun onAddProductClick(){
            val product = viewModel.productSelected.value
            val units = viewModel.units.value

            if(product != null && units != null){
                val productModel = ProductModel(
                    product,
                    units
                )
                val intent = Intent()
                intent.putExtra(PRODUCT_PARAM, productModel)
                setResult(RESULT_OK, intent)
                finish()
            }else{
                showCustomDialog(
                    getString(R.string.error),
                    getString(R.string.must_complete_fields)
                )
            }
        }
    }

    companion object {
        const val DISCOUNTS_PARAM = "DISCOUNTS_PARAM"
        fun getIntent(context: Context, productsInfo: List<ProductInfoModel>): Intent {
            return Intent(context, AddProductActivity::class.java).apply {
                putExtra(DISCOUNTS_PARAM, ArrayList(productsInfo))
            }
        }
    }
}