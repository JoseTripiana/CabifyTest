package com.tripiana.cabifytest.data.repository.network.api

import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET(PRODUCTS_URL)
    suspend fun getProducts(): Response<String>

    companion object {
        const val PRODUCTS_URL = "palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json"
    }
}