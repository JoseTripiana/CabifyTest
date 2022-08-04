package com.tripiana.cabifytest.util.view

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility", "invisible", requireAll = false)
fun View.setVisibility(value: Boolean?, invisible: Boolean?) {
    visibility = if (value == true) View.VISIBLE else if (invisible == true) View.INVISIBLE else View.GONE
}