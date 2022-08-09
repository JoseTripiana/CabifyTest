package com.tripiana.cabifytest.util.ext

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.core.content.ContextCompat

fun Int.toDp(displayMetrics: DisplayMetrics) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics)

fun Int.asResourceColor(context: Context) = ContextCompat.getColor(context, this)