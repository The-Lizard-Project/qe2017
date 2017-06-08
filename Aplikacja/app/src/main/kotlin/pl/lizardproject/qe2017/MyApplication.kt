package pl.lizardproject.qe2017

import android.app.Application
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.session.UserSession

class MyApplication : Application() {
    val databaseFacade = DatabaseFacade(this)
    val userSession = UserSession()
}