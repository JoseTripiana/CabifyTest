package com.tripiana.cabifytest.data.mapper

interface BaseMapper<Entity, Model> {

    fun toModel(entity: Entity): Model
    fun toEntity(model: Model): Entity = throw NotImplementedError("Function Not Implemented")

    fun toEntityList(models: MutableList<Model>): MutableList<Entity> {
        val list = mutableListOf<Entity>()
        models.mapTo(list) { toEntity(it) }
        return list
    }

    fun toModelList(entities: MutableList<Entity>): MutableList<Model> {
        val list = mutableListOf<Model>()
        entities.mapTo(list) { toModel(it) }
        return list
    }
}