package com.tripiana.cabifytest.data.mapper

import com.tripiana.cabifytest.data.entity.DiscountEntity
import com.tripiana.cabifytest.domain.model.DiscountModel

class DiscountMapper(
    private val bulkDiscountMapper: BulkDiscountMapper
) : BaseMapper<DiscountEntity, DiscountModel> {
    override fun toModel(entity: DiscountEntity): DiscountModel =
        with(entity) {
            DiscountModel(
                code,
                hasTwoForOneDisccount,
                bulkDiscountMapper.toModelList(bulkDiscounts.toMutableList())
            )
        }

}