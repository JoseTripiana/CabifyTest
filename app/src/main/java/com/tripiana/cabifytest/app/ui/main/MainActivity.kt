package com.tripiana.cabifytest.app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tripiana.cabifytest.data.repository.network.ProductRepository
import com.tripiana.cabifytest.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
        }
    }

    private val repository: ProductRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO){
            getData()
        }
    }

    // OKHttp Test fetching data from url
    suspend fun getData() {
//        withContext(Dispatchers.IO) {
//            val client = OkHttpClient()
//            val request = okhttp3.Request.Builder()
//                .url("https://gist.githubusercontent.com/palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
//                .build()
//            val response = client.newCall(request).execute()
//            val body = response.body?.string()
//            println("DATA $body")
//        }

        withContext(Dispatchers.IO){
            val data = repository.getProducts()
            println("DATA $data")
        }
    }
}