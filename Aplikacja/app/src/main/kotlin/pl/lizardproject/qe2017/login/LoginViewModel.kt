package pl.lizardproject.qe2017.login

import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toAppModel
import pl.lizardproject.qe2017.itemlist.ItemListActivity
import pl.lizardproject.qe2017.model.User
import pl.lizardproject.qe2017.register.RegisterActivity
import pl.lizardproject.qe2017.session.UserSession
import rx.Single
import rx.Subscription

class LoginViewModel(private val databaseFacade: DatabaseFacade, private val userSession: UserSession) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")
    val showSpinner = ObservableField(false)

    private var subscription: Subscription? = null

    fun loginCommand(view: View) {
        subscription = Single.just(username.get())
                .doOnSubscribe { showSpinner.set(true) }
                .flatMap { databaseFacade.loadUser(it) }
                .flatMap { if (it != null) Single.just(it) else Single.error(Exception(view.context.getString(R.string.loginError))) }
                .doOnError { showSpinner.set(false) }
                .subscribe(
                        { loginUser(it.toAppModel(), view.context) },
                        { errorText.set(it.message) }
                          )
    }

    fun registerCommand(view: View) {
        view.context.startActivity(Intent(view.context, RegisterActivity::class.java))
    }

    fun dispose() {
        subscription?.unsubscribe()
    }

    private fun loginUser(user: User, context: Context) {
        userSession.start(user)
        context.startActivity(Intent(context, ItemListActivity::class.java))
    }
}