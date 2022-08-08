package com.tripiana.cabifytest.app.ui.main

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.tripiana.cabifytest.app.ui.base.BaseActivity
import com.tripiana.cabifytest.databinding.ActivityMainBinding
import com.tripiana.cabifytest.domain.model.ProductModel
import com.tripiana.cabifytest.util.livedata.observeOnce
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            productsInfoEmpty = viewModel.productsInfoEmpty
            handler = ClickHandler()
        }
    }

    private val viewModel: MainViewModel by viewModel()

    private val adapter by lazy { MainAdapter() }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == RESULT_OK) {
            val product = result.data?.getParcelableExtra<ProductModel>(PRODUCT_PARAM)
            //TODO Sum units
            product?.let {
                viewModel.productList.value.orEmpty().let { productList ->
                    val updatedList = productList.toMutableList()
                    updatedList.add(it)
                    viewModel.productList.value = updatedList
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        with(binding.recyclerView){
            adapter = this@MainActivity.adapter
        }

        viewModel.productList.observe(this){
            adapter.update(it)
        }

        showLoading()
        viewModel.initProducts().observeOnce(this, getResultObjectObserver({
            hideLoading()
        }))
    }

    inner class ClickHandler {
        fun onAddProductClick(){
            startForResult.launch(navigator.getAddProductActivityIntent(viewModel.productsInfoList.value.orEmpty()))
        }

        fun onPayClicked(){

        }
    }

    companion object {
        const val PRODUCT_PARAM = "PRODUCT_PARAM"
    }

}