package com.tripiana.cabifytest.util.view

import android.content.res.Resources
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("android:visibility", "invisible", requireAll = false)
fun View.setVisibility(value: Boolean?, invisible: Boolean?) {
    visibility = if (value == true) View.VISIBLE else if (invisible == true) View.INVISIBLE else View.GONE
}

@BindingAdapter("android:text")
fun setText(view: TextView, value: Int) {
    if (view.text != null && view.text.toString().isNotEmpty()) {
        try {
            val d = if (view.text.toString().isInt()) view.text.toString().toInt() else 0
            if (d == value) return
            else setTextOrRes(view, value)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            return
        }
    } else if (value == 0 && view.text != null && view.text.isEmpty()) {
        return
    } else {
        setTextOrRes(view, value)
    }
}

private fun setTextOrRes(view: TextView, value: Int) {
    try {
        val res = view.resources.getString(value)
        view.text = res
    } catch (e: Resources.NotFoundException) {
        view.text = value.toString()
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getText(view: TextView): Int {
    return if (view.text != null && view.text.toString().isNotEmpty())
        return view.text.toString().toInt()
    else
        0
}
