package pl.lizardproject.qe2017.register

import android.databinding.ObservableField
import android.view.View
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toAppModel
import pl.lizardproject.qe2017.database.converter.toDbModel
import pl.lizardproject.qe2017.model.User
import pl.lizardproject.qe2017.navigation.AppNavigator
import pl.lizardproject.qe2017.session.UserSession
import rx.Single
import rx.Subscription

class RegisterViewModel(private val databaseFacade: DatabaseFacade, private val userSession: UserSession, private val appNavigator: AppNavigator) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")
    val showSpinner = ObservableField(false)

    private var subscription: Subscription? = null

    fun registerCommand(view: View) {
        if (username.get().isNotBlank() && password.get().isNotBlank()) {
            subscription = databaseFacade.loadUser(username.get())
                    .doOnSubscribe { showSpinner.set(true) }
                    .flatMap {
                        if (it == null) {
                            databaseFacade.saveUser(User(username.get(), password.get()).toDbModel())
                        } else {
                            Single.error(Exception(view.context.getString(R.string.registerErrorUserExists)))
                        }
                    }
                    .doOnError { showSpinner.set(false) }
                    .subscribe(
                            { it -> performUser(it.toAppModel()) },
                            { errorText.set(it.message) })
        } else {
            errorText.set(view.context.getString(R.string.registerError))
        }
    }

    fun dispose() {
        subscription?.unsubscribe()
    }

    private fun performUser(user: User) {
        userSession.start(user)
        appNavigator.openItemListActivity()
    }
}