package com.tripiana.cabifytest.util.result

sealed class ResultObject<T> {

    companion object {
        fun <T> onSuccess (data: T): ResultObject<T> = Success(data)
        fun <T> onError (error: Throwable): ResultObject<T> = Error(error)
    }

    data class Success<T>(val data: T) : ResultObject<T>()
    data class Error<T>(val error: Throwable) : ResultObject<T>()

}