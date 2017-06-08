package pl.lizardproject.qe2017.login

import android.content.Intent
import android.databinding.ObservableField
import android.support.design.widget.Snackbar
import android.view.View
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.itemlist.ItemListActivity
import pl.lizardproject.qe2017.model.User
import pl.lizardproject.qe2017.session.UserSession

class LoginViewModel(private val databaseFacade: DatabaseFacade, private val userSession: UserSession) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String?>(null)

    fun loginCommand(view: View) {
        val user = User(name = username.get(), password = password.get())
        if (databaseFacade.hasUser(user)) {
            userSession.start(user)
            view.context.startActivity(Intent(view.context, ItemListActivity::class.java))
        } else {
            errorText.set(view.context.getString(R.string.loginError))
            Snackbar.make(view, view.context.getString(R.string.loginError), Snackbar.LENGTH_SHORT).show()
        }
    }

    fun registerCommand(view: View) {

    }
}