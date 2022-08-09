package com.tripiana.cabifytest.app.ui.addproduct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.app.ui.addproduct.dialog.ProductBottomSheetDialog
import com.tripiana.cabifytest.app.ui.addproduct.strategy.ProductDetailStrategy
import com.tripiana.cabifytest.app.ui.base.BaseActivity
import com.tripiana.cabifytest.app.ui.main.MainActivity.Companion.PRODUCT_PARAM
import com.tripiana.cabifytest.databinding.ActivityAddProductBinding
import com.tripiana.cabifytest.domain.model.ProductInfoModel
import com.tripiana.cabifytest.domain.model.ProductModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivity : BaseActivity() {


    private val binding: ActivityAddProductBinding by lazy {
        ActivityAddProductBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@ProductDetailActivity
            productSelected = viewModel.productSelected
            units = viewModel.units
            hasTwoForOne = viewModel.hasTwoForOne
            pricePerUnit = viewModel.pricePerUnit
            total = viewModel.total
            saved = viewModel.saved
            strategy = this@ProductDetailActivity.strategy
            handler = ClickHandler()
        }
    }

    private val viewModel: ProductDetailViewModel by viewModel()

    private val strategy by lazy { intent.extras?.getParcelable<ProductDetailStrategy>(STRATEGY_PARAM) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        intent.extras?.getParcelableArrayList<ProductInfoModel>(PRODUCTS_INFO_PARAM)?.let {
            viewModel.productInfoModelList.addAll(it)

            strategy?.getProduct()?.let { product ->
                viewModel.productSelected.value = it.firstOrNull { productInfo -> productInfo.id == product.id }
                viewModel.units.value = product.units
            }
        }
    }

    inner class ClickHandler {
        fun onProductClick() {
            if (strategy?.canSelectProduct() == true) {
                ProductBottomSheetDialog(
                    viewModel.productInfoModelList
                ) {
                    viewModel.productSelected.value = it
                    viewModel.units.value = 0
                }.show(supportFragmentManager, null)
            }
        }

        fun onAddProductClick() {
            val product = viewModel.productSelected.value
            val units = viewModel.units.value

            if (product != null && units != null && units > 0) {
                val productModel = ProductModel(
                    product,
                    units
                )
                val intent = Intent()
                intent.putExtra(PRODUCT_PARAM, productModel)
                intent.putExtra(STRATEGY_PARAM, strategy)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                showCustomDialog(
                    getString(R.string.error),
                    getString(R.string.must_complete_fields)
                )
            }
        }
    }

    companion object {
        const val PRODUCTS_INFO_PARAM = "PRODUCTS_INFO_PARAM"
        const val STRATEGY_PARAM = "STRATEGY_PARAM"
        fun getIntent(context: Context, productsInfo: List<ProductInfoModel>, strategy: ProductDetailStrategy): Intent {
            return Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(PRODUCTS_INFO_PARAM, ArrayList(productsInfo))
                putExtra(STRATEGY_PARAM, strategy)
            }
        }
    }
}