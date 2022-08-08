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
): Parcelable, Idable {

    override val id: String
        get() = product.id

    fun getUnitText(context: Context) =
        context.getString(R.string.x_units, units)

    fun getPricePerUnitText(context: Context): String = context.getString(R.string.price_per_unit, getPricePerUnit())

    private fun getPricePerUnit(): Float {
        return if (units > 0) {
            val twoForOneTimes = if (product.discounts?.hasTwoForOneDisccount == true) (units / 2) else 0
            val newPricePerUnit = product.discounts?.bulkDiscounts?.maxByOrNull { bulkDiscount -> bulkDiscount.units < (units) }?.price ?: product.price
            ((units - twoForOneTimes) * newPricePerUnit) / units
        } else
            0f
    }

    fun getTotalPriceText(context: Context): String {
        val total = units.toFloat() * getPricePerUnit()
        return context.getString(R.string.total_price, 0)
    }

    fun getTotalSavedText(context: Context): String{
        val saved = (product.price * units) - (units * getPricePerUnit())
        return context.getString(R.string.total_saved, saved)
    }

    companion object {
        fun getUnitText(context: Context, product: ProductModel) =
            context.getString(R.string.x_units, product.units)

        fun getPricePerUnitText(context: Context, product: ProductModel): String {


            return context.getString(R.string.price_per_unit, getPricePerUnit(product))
        }

        private fun getPricePerUnit(product: ProductModel): Float {
            return if (product.units > 0) {
                val twoForOneTimes = if (product.product.discounts?.hasTwoForOneDisccount == true) (product.units / 2) else 0
                val newPricePerUnit = product.product.discounts?.bulkDiscounts?.maxByOrNull { bulkDiscount -> bulkDiscount.units < (product.units) }?.price ?: product.product.price
                ((product.units - twoForOneTimes) * newPricePerUnit) / product.units
            } else
                0f
        }

        fun getTotalPriceText(context: Context, product: ProductModel): String {
            val total = product.units.toFloat() * getPricePerUnit(product)
            return context.getString(R.string.total_price, 0)
        }

        fun getTotalSavedText(context: Context, product: ProductModel): String{
            val saved = (product.product.price * product.units) - (product.units * getPricePerUnit(product))
            return context.getString(R.string.total_saved, saved)
        }
    }

}