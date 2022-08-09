package com.tripiana.cabifytest.data.mapper

import com.tripiana.cabifytest.data.entity.ProductTypeEntity
import com.tripiana.cabifytest.domain.model.ProductTypeModel

class ProductTypeMapper : BaseMapper<ProductTypeEntity, ProductTypeModel> {
    override fun toModel(entity: ProductTypeEntity): ProductTypeModel =
        with(entity) {
            ProductTypeModel(
                code,
                name,
                price
            )
        }
}