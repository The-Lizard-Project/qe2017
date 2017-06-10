package pl.lizardproject.qe2017.register

import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toDbModel
import pl.lizardproject.qe2017.itemlist.ItemListActivity
import pl.lizardproject.qe2017.model.User
import pl.lizardproject.qe2017.session.UserSession
import rx.Single

class RegisterViewModel(private val databaseFacade: DatabaseFacade, private val userSession: UserSession) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")
    val showSpinner = ObservableField(false)

    fun registerCommand(view: View) {
        if (username.get().isNotBlank() && password.get().isNotBlank()) {
            showSpinner.set(true)
            val user = User(username.get(), password.get())
            val dbUser = user.toDbModel()

            databaseFacade.hasUser(dbUser)
                    .flatMap {
                        if (!it) {
                            databaseFacade.saveUser(dbUser)
                        } else {
                            Single.error(Exception(view.context.getString(R.string.registerErrorUserExists)))
                        }
                    }
//                    .doAfterTerminate { showSpinner.set(false) }
                    .doOnError { showSpinner.set(false) }
                    .subscribe(
                            { performUser(user, view.context) },
                            { errorText.set(it.message) }
                              )
        } else {
            errorText.set(view.context.getString(R.string.registerError))
        }
    }

    private fun performUser(user: User, context: Context) {
        userSession.start(user)
        context.startActivity(Intent(context, ItemListActivity::class.java))
    }
}