package com.tripiana.cabifytest.data.mapper

import com.tripiana.cabifytest.data.entity.BulkDiscountEntity
import com.tripiana.cabifytest.domain.model.BulkDiscountModel

class BulkDiscountMapper: BaseMapper<BulkDiscountEntity, BulkDiscountModel> {
    override fun toModel(entity: BulkDiscountEntity): BulkDiscountModel =
        with(entity){
            BulkDiscountModel(
                units,
                price
            )
        }
}