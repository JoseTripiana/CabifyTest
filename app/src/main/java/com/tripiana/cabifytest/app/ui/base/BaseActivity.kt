package com.tripiana.cabifytest.app.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.databinding.ActivityBaseBinding
import com.tripiana.cabifytest.util.result.ResultObserver
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity(), BaseErrorListener {

    val navigator by inject<Navigator>()

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

    fun showCustomDialog(
        title: String,
        description: String,
        cancelable: Boolean = true,
        positiveButton: String = getString(android.R.string.ok),
        positiveListener: (() -> Unit)? = null,
    ){
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(positiveButton) { dialog, _ ->
                positiveListener?.invoke()
                dialog.dismiss()
            }
            .setCancelable(cancelable)
            .create()
        dialog.show()
    }


    override fun onError(throwable: Throwable) {
        hideLoading()
        showCustomDialog(
            title = getString(R.string.error),
            description = getString(R.string.unexpected_error)
        )
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