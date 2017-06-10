package pl.lizardproject.qe2017.database.model

import io.requery.*
import pl.lizardproject.qe2017.model.Category
import pl.lizardproject.qe2017.model.Priority

@Entity
interface DbItem : Persistable {
    @get:Key @get:Generated var id: Int

    @get:Column(unique = true) var name: String
    var isChecked: Boolean
    var category: Category
    var priority: Priority

    @get:ManyToOne(cascade = arrayOf(CascadeAction.NONE)) var user: DbUser
}