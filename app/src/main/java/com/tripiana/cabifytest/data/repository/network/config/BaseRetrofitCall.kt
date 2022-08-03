package com.tripiana.cabifytest.data.repository.network.config

import retrofit2.Response
import retrofit2.Retrofit

class BaseRetrofitCall(
    val retrofit: Retrofit
) {

    suspend fun <T> makeCall(
        call: suspend () -> Response<T>
    ): T =
        try {
            call().let { response ->
                if(response.isSuccessful && response.body() != null) {
                    response.body()!!
                } else {
                    throw Exception()
                }
            }
        } catch(e: RetrofitIOException){
            throw e.cause ?: e
        }
}