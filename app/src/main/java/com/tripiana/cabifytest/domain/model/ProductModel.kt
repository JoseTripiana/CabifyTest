package com.tripiana.cabifytest.domain.model

import android.content.Context
import android.os.Parcelable
import com.tripiana.cabifytest.R
import com.tripiana.cabifytest.app.ui.base.adapter.Idable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    val product: ProductInfoModel,
    val units: Int
) : Parcelable, Idable {

    override val id: String
        get() = product.id

    fun getPricePerUnitText(context: Context): String = context.getString(R.string.price_per_unit, getPricePerUnit())

    private fun getPricePerUnit(): Float {
        return if (units > 0) {
            val twoForOneTimes = if (product.discounts?.hasTwoForOneDisccount == true) (units / 2) else 0
            val newPricePerUnit = product.discounts?.bulkDiscounts?.maxByOrNull { bulkDiscount -> bulkDiscount.units < (units) }?.price ?: product.price
            ((units - twoForOneTimes) * newPricePerUnit) / units
        } else
            0f
    }

    fun getTotalPrice(): Float = units.toFloat() * getPricePerUnit()

    fun getTotalPriceText(context: Context): String {
        return context.getString(R.string.total_price, getTotalPrice())
    }

    fun getTotalSaved(): Float =
        (product.price * units) - (units * getPricePerUnit())

    fun getTotalSavedText(context: Context): String {
        return context.getString(R.string.total_saved, getTotalSaved())
    }


}