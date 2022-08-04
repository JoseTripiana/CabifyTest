package com.tripiana.cabifytest.app.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.databinding.ActivityBaseBinding
import com.tripiana.cabifytest.util.result.ResultObserver

abstract class BaseActivity : AppCompatActivity(), BaseErrorListener {

    private val baseBinding by lazy {
        ActivityBaseBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@BaseActivity
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(baseBinding.root)
    }

    override fun setContentView(view: View?) {
        baseBinding.baseContainer.addView(view)
    }

    open fun showLoading() {
        baseBinding.flLoading.visibility = View.VISIBLE
    }

    open fun hideLoading(){
        baseBinding.flLoading.visibility = View.GONE
    }


    override fun onError(throwable: Throwable) {
        hideLoading()
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