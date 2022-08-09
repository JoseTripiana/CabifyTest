package com.tripiana.cabifytest.util.view

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text")
fun TextView.setText(value: String?) {
    text = value.orEmpty()
}

@BindingAdapter("android:text")
fun TextView.setText(value: Number) {
    text = value.toString()
}

