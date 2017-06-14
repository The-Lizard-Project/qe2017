package pl.lizardproject.qe2017.itemlist

import android.databinding.ObservableArrayList
import android.util.Log
import android.view.View
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toAppModel
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.navigation.AppNavigator
import pl.lizardproject.qe2017.session.UserSession

class ItemListViewModel(databaseFacade: DatabaseFacade, private val userSession: UserSession, private val appNavigator: AppNavigator) {

    val items = ObservableArrayList<Item>()

    private var subscription = databaseFacade.loadItems(userSession.user!!.id!!)
            .map { it.map { it.toAppModel() } }
            .subscribe({ values ->
                items.clear()
                items.addAll(values)
            }, { ex -> Log.e("TAG", ex.message, ex) })

    fun addItemCommand(ignored: View) {
        appNavigator.openEditItemActivity()
    }

    fun logout() {
        userSession.end()
        appNavigator.openLoginActivity()
    }

    fun dispose() {
        subscription.unsubscribe()
    }
}