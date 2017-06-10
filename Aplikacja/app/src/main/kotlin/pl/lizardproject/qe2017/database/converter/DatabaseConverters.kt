package pl.lizardproject.qe2017.database.converter

import pl.lizardproject.qe2017.database.model.DbItemEntity
import pl.lizardproject.qe2017.database.model.DbUserEntity
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.model.User

fun DbItemEntity.toAppModel() = Item(id, name, category, priority, isChecked)

fun Item.toDbModel(): DbItemEntity {
    val dbModel = DbItemEntity()
    dbModel.id = id ?: 0
    dbModel.name = name
    dbModel.category = category
    dbModel.priority = priority
    dbModel.isChecked = isChecked
    return dbModel
}

fun DbUserEntity.toAppModel() = User(name, password)

fun User.toDbModel(): DbUserEntity {
    val dbModel = DbUserEntity()
    dbModel.name = name
    dbModel.password = password
    return dbModel
}