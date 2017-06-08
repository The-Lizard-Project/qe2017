package pl.lizardproject.qe2017.database.model

import io.requery.Entity
import io.requery.Generated
import io.requery.Key
import io.requery.Persistable

@Entity
interface DbUser : Persistable {
    @get:Key @get:Generated var id: Int

    var name: String
    var password: String
}