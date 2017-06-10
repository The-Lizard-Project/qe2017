package pl.lizardproject.qe2017.login

import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toDbModel
import pl.lizardproject.qe2017.itemlist.ItemListActivity
import pl.lizardproject.qe2017.model.User
import pl.lizardproject.qe2017.register.RegisterActivity
import pl.lizardproject.qe2017.session.UserSession
import rx.Single

class LoginViewModel(private val databaseFacade: DatabaseFacade, private val userSession: UserSession) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")
    val showSpinner = ObservableField(false)

    fun loginCommand(view: View) {
        val user = User(username.get(), password.get())

        Single.just(user)
                .doOnSubscribe { showSpinner.set(true) }
                .map { it.toDbModel() }
                .flatMap { databaseFacade.hasUser(it) }
                .flatMap { if (it) Single.just(user) else Single.error(Exception(view.context.getString(R.string.loginError))) }
//                .doAfterTerminate { showSpinner.set(false) }
                .doOnError { showSpinner.set(false) }
                .subscribe(
                        { loginUser(it, view.context) },
                        { errorText.set(it.message) }
                          )
    }

    fun registerCommand(view: View) {
        view.context.startActivity(Intent(view.context, RegisterActivity::class.java))
    }

    private fun loginUser(user: User, context: Context) {
        userSession.start(user)
        context.startActivity(Intent(context, ItemListActivity::class.java))
    }
}