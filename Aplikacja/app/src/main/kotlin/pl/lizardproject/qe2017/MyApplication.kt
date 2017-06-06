package pl.lizardproject.qe2017

import android.app.Application
import pl.lizardproject.qe2017.database.DatabaseFacade

class MyApplication : Application() {
    val databaseFacade = DatabaseFacade(this)
}