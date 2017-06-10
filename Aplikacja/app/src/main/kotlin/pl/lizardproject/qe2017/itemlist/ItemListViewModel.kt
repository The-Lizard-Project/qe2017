package pl.lizardproject.qe2017.itemlist

import android.databinding.ObservableArrayList
import android.util.Log
import android.view.View
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toAppModel
import pl.lizardproject.qe2017.edititem.Henson
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.session.UserSession

class ItemListViewModel(databaseFacade: DatabaseFacade, userSession: UserSession) {

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

    fun dispose() {
        subscription.unsubscribe()
    }
}