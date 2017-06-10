package pl.lizardproject.qe2017.database.model

import io.requery.*

@Entity
interface DbUser : Persistable {
    @get:Key @get:Generated var id: Int

    @get:Column(unique = true) var name: String
    var password: String
}