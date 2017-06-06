package pl.lizardproject.qe2017.database.converter

import pl.lizardproject.qe2017.database.model.DbItemEntity
import pl.lizardproject.qe2017.model.Item

fun DbItemEntity.toAppModel() = Item(id, name, category, priority, isChecked)

fun Item.toDbModel(dbItem: DbItemEntity? = null): DbItemEntity {
    val dbModel = dbItem ?: DbItemEntity()
    dbModel.name = name
    dbModel.category = category
    dbModel.priority = priority
    dbModel.isChecked = isChecked
    return dbModel
}