package pl.lizardproject.qe2017.login

import android.databinding.ObservableField
import android.view.View
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toAppModel
import pl.lizardproject.qe2017.model.User
import pl.lizardproject.qe2017.navigation.AppNavigator
import pl.lizardproject.qe2017.session.UserSession
import rx.Single
import rx.Subscription

class LoginViewModel(private val databaseFacade: DatabaseFacade, private val userSession: UserSession, private val appNavigator: AppNavigator) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")
    val showSpinner = ObservableField(false)

    private var subscription: Subscription? = null

    fun loginCommand(view: View) {
        subscription = Single.just(User(username.get(), password.get()))
                .doOnSubscribe { showSpinner.set(true) }
                .flatMap { databaseFacade.loadUser(it.name) }
                .flatMap { if (it != null) Single.just(it) else Single.error(Exception(view.context.getString(R.string.loginError))) }
                .doOnError { showSpinner.set(false) }
                .subscribe(
                        { loginUser(it.toAppModel()) },
                        { errorText.set(it.message) })
    }

    fun registerCommand(ignored: View) {
        appNavigator.openRegisterActivity()
    }

    fun dispose() {
        subscription?.unsubscribe()
    }

    private fun loginUser(user: User) {
        userSession.start(user)
        appNavigator.openItemListActivity()
    }
}