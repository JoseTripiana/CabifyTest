package com.tripiana.cabifytest.util.result

import androidx.lifecycle.Observer
import com.tripiana.cabifytest.app.ui.base.BaseErrorListener

abstract class ResultObserver<T>(private val errorListener: BaseErrorListener) : Observer<ResultObject<T>> {

    override fun onChanged(t: ResultObject<T>?) {
        t?.let {
            when (it) {
                is ResultObject.Success<T> -> onReceived(it.data)
                is ResultObject.Error<T> -> onError(it.error)
            }
        }
    }

    abstract fun onReceived(data: T)
    open fun onError(error: Throwable) = errorListener.onError(error)
}