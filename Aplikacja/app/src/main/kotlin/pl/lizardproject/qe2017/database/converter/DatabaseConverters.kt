package pl.lizardproject.qe2017.database.converter

import pl.lizardproject.qe2017.database.model.DbItem
import pl.lizardproject.qe2017.database.model.DbItemEntity
import pl.lizardproject.qe2017.database.model.DbUser
import pl.lizardproject.qe2017.database.model.DbUserEntity
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.model.User

fun DbItem.toAppModel() = Item(id, name, category, priority, user.toAppModel(), isChecked)

fun Item.toDbModel(): DbItemEntity {
    val dbModel = DbItemEntity()
    dbModel.id = id ?: 0
    dbModel.name = name
    dbModel.category = category
    dbModel.priority = priority
    dbModel.user = user.toDbModel()
    dbModel.isChecked = isChecked
    return dbModel
}

fun DbUser.toAppModel() = User(name, password, id)

fun User.toDbModel(): DbUserEntity {
    val dbModel = DbUserEntity()
    dbModel.id = id ?: 0
    dbModel.name = name
    dbModel.password = password
    return dbModel
}