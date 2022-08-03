package com.tripiana.cabifytest.app.ui.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.util.result.ResultObserver

abstract class BaseActivity : AppCompatActivity(), BaseErrorListener {



    override fun onError(throwable: Throwable) {
        //TODO hideLoading
        AlertDialog.Builder(this)
            .setTitle(R.string.error)
            .setMessage(R.string.unexpected_error)
            .setPositiveButton(android.R.string.ok){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun <T> getResultObjectObserver(
        onSuccess: (T) -> Unit,
        onError: ((Throwable, _super: (Throwable) -> Unit) -> Unit)? = null
    ) = object : ResultObserver<T>(this) {
        override fun onReceived(data: T) {
            onSuccess.invoke(data)
        }

        override fun onError(error: Throwable) {
            onError?.let {
                it(error) { super.onError(error) }
            } ?: super.onError(error)
        }
    }

}