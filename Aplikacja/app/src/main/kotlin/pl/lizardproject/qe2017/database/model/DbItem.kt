package pl.lizardproject.qe2017.database.model

import io.requery.Entity
import io.requery.Generated
import io.requery.Key
import io.requery.Persistable
import pl.lizardproject.qe2017.model.Category
import pl.lizardproject.qe2017.model.Priority

@Entity
interface DbItem : Persistable {
    @get:Key @get:Generated var id: Int

    var name: String
    var isChecked: Boolean
    var category: Category
    var priority: Priority
}