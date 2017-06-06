package pl.lizardproject.qe2017.itemlist

import android.databinding.ObservableArrayList
import android.util.Log
import android.view.View
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.edititem.Henson
import pl.lizardproject.qe2017.model.Item

class ItemListViewModel(databaseFacade: DatabaseFacade) {

    val items = ObservableArrayList<Item>()

    private var subscription = databaseFacade.loadItems()
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