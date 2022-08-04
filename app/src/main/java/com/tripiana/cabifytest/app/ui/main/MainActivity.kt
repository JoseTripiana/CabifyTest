package com.tripiana.cabifytest.app.ui.main

import android.os.Bundle
import com.tripiana.cabifytest.app.ui.base.BaseActivity
import com.tripiana.cabifytest.databinding.ActivityMainBinding
import com.tripiana.cabifytest.util.livedata.observeOnce
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            productsInfoEmpty = viewModel.productsInfoEmpty
        }
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        showLoading()
        viewModel.initProducts().observeOnce(this, getResultObjectObserver({
            hideLoading()
        }))
    }

    inner class ClickHandler {
        fun onAddProductClick(){

        }

        fun onPayClicked(){

        }
    }

}