package pl.lizardproject.qe2017.database.converter

import pl.lizardproject.qe2017.database.model.DbItemEntity
import pl.lizardproject.qe2017.database.model.DbUser
import pl.lizardproject.qe2017.database.model.DbUserEntity
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.model.User

fun DbItemEntity.toAppModel() = Item(id, name, category, priority, isChecked)

fun Item.toDbModel(dbItem: DbItemEntity? = null): DbItemEntity {
    val dbModel = dbItem ?: DbItemEntity()
    dbModel.name = name
    dbModel.category = category
    dbModel.priority = priority
    dbModel.isChecked = isChecked
    return dbModel
}

fun DbUser.toAppModel() = User(id, name, password)

fun User.toDbModel(dbUser: DbUserEntity? = null): DbUserEntity {
    val dbModel = dbUser ?: DbUserEntity()
    dbModel.name = name
    dbModel.password = password
    return dbModel
}