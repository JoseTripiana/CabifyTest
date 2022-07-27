package com.tripiana.cabifytest.data.repository.network

import com.tripiana.cabifytest.data.repository.network.api.ProductApi
import retrofit2.Retrofit

class ProductRepository(
    private val retrofit: Retrofit,
    private val productApi: ProductApi
) {


    suspend fun getProducts(): String?{

        val call = productApi.getProducts()

        return if(call.isSuccessful){
            val response = call.body()
            response
        }else{
            throw Exception("${call.errorBody()}")
        }
    }
}