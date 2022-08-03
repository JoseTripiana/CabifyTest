package com.tripiana.cabifytest.app.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tripiana.cabifytest.app.ui.base.BaseActivity
import com.tripiana.cabifytest.data.repository.network.ProductRepository
import com.tripiana.cabifytest.databinding.ActivityMainBinding
import com.tripiana.cabifytest.util.observeOnce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
        }
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        viewModel.getProducts().observeOnce(this, getResultObjectObserver({
            Toast.makeText(this, it.map {product -> product.code }.joinToString(), Toast.LENGTH_LONG).show()
        }))

        viewModel.getDiscounts().observeOnce(this, getResultObjectObserver({
            Toast.makeText(this, it.map {discount -> discount.toString() }.joinToString(), Toast.LENGTH_LONG).show()
        }))
    }
}