package com.tripiana.cabifytest.data.repository.network.api

import com.tripiana.cabifytest.data.repository.network.request.DiscountsRequest
import com.tripiana.cabifytest.data.repository.network.request.ProductsTypeRequest
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET(PRODUCTS_URL)
    suspend fun getProducts(): Response<ProductsTypeRequest>

    @GET(DISCOUNTS_URL)
    suspend fun getDiscounts(): Response<DiscountsRequest>

    companion object {
        const val PRODUCTS_URL = "palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json"
        const val DISCOUNTS_URL = "JoseTripiana/fedf8eaab40e1809483cb90bbe5fb326/raw/fbf21c79196db00cffcae3e91c39eab57ea4ab64/Discounts.json"
    }
}