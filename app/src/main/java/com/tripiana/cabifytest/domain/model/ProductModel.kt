package com.tripiana.cabifytest.domain.model

import android.content.Context
import com.tripiana.cabifytest.R

data class ProductModel(
    val type: ProductTypeModel
    //TODO
){

    //TODO
    fun getUnitText(context: Context) =
        context.getString(R.string.units, 0)
    //TODO
    fun getPricePerUnitText(context: Context) =
        context.getString(R.string.price_per_unit, 0)

    //TODO
    fun getTotalPriceText(context: Context) = context.getString(R.string.total_price, 0)

    //TODO
    fun getTotalSavedText(context: Context) = context.getString(R.string.total_saved, 0)

}