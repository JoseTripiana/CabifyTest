package com.tripiana.cabifytest.app.ui.main

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.app.ui.addproduct.ProductDetailActivity.Companion.STRATEGY_PARAM
import com.tripiana.cabifytest.app.ui.addproduct.strategy.AddProductStrategy
import com.tripiana.cabifytest.app.ui.addproduct.strategy.EditProductStrategy
import com.tripiana.cabifytest.app.ui.addproduct.strategy.ProductDetailStrategy
import com.tripiana.cabifytest.app.ui.base.BaseActivity
import com.tripiana.cabifytest.databinding.ActivityMainBinding
import com.tripiana.cabifytest.domain.model.ProductModel
import com.tripiana.cabifytest.util.livedata.observeOnce
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            productsInfoEmpty = viewModel.productsInfoEmpty
            productsEmpty = viewModel.productsEmpty
            totalPrice = viewModel.totalPrice
            totalSaved = viewModel.totalSaved
            handler = ClickHandler()
        }
    }

    private val viewModel: MainViewModel by viewModel()

    private val mainAdapter by lazy {
        MainAdapter(object : ProductListHandler {
            override fun onItemClick(item: ProductModel) {
                startForResult.launch(
                    navigator.getAddProductActivityIntent(
                        viewModel.productsInfoList.value.orEmpty(),
                        EditProductStrategy(item)
                    )
                )
            }

            override fun onDeleteClick(item: ProductModel) {
                viewModel.deleteProduct(item)
            }
        })
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val product = result.data?.getParcelableExtra<ProductModel>(PRODUCT_PARAM)
            val strategy = result.data?.getParcelableExtra<ProductDetailStrategy>(STRATEGY_PARAM)
            product?.let {
                viewModel.addProduct(it, strategy)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        title = getString(R.string.shopping_basket)

        with(binding.recyclerView) {
            adapter = mainAdapter
        }

        viewModel.productList.observe(this) {
            mainAdapter.update(it)
        }

        showLoading()
        viewModel.initProducts().observeOnce(this, getResultObjectObserver({
            hideLoading()
        }))
    }

    inner class ClickHandler {
        fun onAddProductClick() {
            startForResult.launch(
                navigator.getAddProductActivityIntent(
                    viewModel.productsInfoList.value.orEmpty(),
                    AddProductStrategy()
                )
            )
        }

        fun onPayClicked() {
            if (viewModel.productList.value.isNullOrEmpty()) {
                showCustomDialog(
                    getString(R.string.error),
                    getString(R.string.no_items_on_basket)
                )
            } else {
                val totalAndSaved = viewModel.pay()
                showCustomDialog(
                    getString(R.string.purchase_summary),
                    "${getString(R.string.total_price, totalAndSaved.first)}\n${getString(R.string.total_saved, totalAndSaved.second)}"
                )
            }
        }
    }

    companion object {
        const val PRODUCT_PARAM = "PRODUCT_PARAM"
    }

}