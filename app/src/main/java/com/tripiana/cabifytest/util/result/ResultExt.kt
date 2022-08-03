package com.tripiana.cabifytest.util.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal const val DEFAULT_TIMEOUT = 5000L

suspend fun <T> toResult(closure: suspend () -> T): ResultObject<T> {
    return try {
        ResultObject.onSuccess(closure())
    } catch (e: Exception) {
        ResultObject.onError(e)
    }
}

fun <T> toResultLiveData(
    context: CoroutineContext = EmptyCoroutineContext,
    timeoutInMs: Long = DEFAULT_TIMEOUT,
    closure: suspend () -> T
): LiveData<ResultObject<T>> {
    return liveData(context, timeoutInMs){
        emit(toResult(closure))
    }
}