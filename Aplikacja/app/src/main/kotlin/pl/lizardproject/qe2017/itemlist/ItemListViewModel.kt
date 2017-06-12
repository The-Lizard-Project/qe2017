package pl.lizardproject.qe2017.itemlist

import android.content.Context
import android.content.Intent
import android.databinding.ObservableArrayList
import android.util.Log
import android.view.View
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toAppModel
import pl.lizardproject.qe2017.edititem.Henson
import pl.lizardproject.qe2017.login.LoginActivity
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.session.UserSession

class ItemListViewModel(databaseFacade: DatabaseFacade, private val userSession: UserSession) {

    val items = ObservableArrayList<Item>()

    private var subscription = databaseFacade.loadItems(userSession.user!!.id!!)
            .map { it.map { it.toAppModel() } }
            .subscribe({ values ->
                items.clear()
                items.addAll(values)
            }, { ex -> Log.e("TAG", ex.message, ex) })

    fun addItemCommand(view: View) {
        view.context.startActivity(
                Henson.with(view.context)
                        .gotoEditItemActivity()
                        .build())
    }

    fun logout(context: Context) {
        userSession.end()
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun dispose() {
        subscription.unsubscribe()
    }
}