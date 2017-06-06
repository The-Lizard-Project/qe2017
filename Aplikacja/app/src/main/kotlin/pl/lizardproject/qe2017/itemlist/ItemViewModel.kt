package pl.lizardproject.qe2017.itemlist

import android.databinding.ObservableField
import android.view.View
import android.widget.CompoundButton
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.edititem.Henson
import pl.lizardproject.qe2017.model.Item

class ItemViewModel(item: Item, private val databaseFacade: DatabaseFacade) {

    val item = ObservableField(item)

    fun onCheckChangedCommand(ignored: CompoundButton, isChecked: Boolean) {
        if (item.get().isChecked != isChecked) {
            databaseFacade.saveItem(item.get().checkItem(isChecked))
        }
    }

    fun onDeleteClickCommand(ignored: View) {
        databaseFacade.deleteItem(item.get())
    }

    fun onClickCommand(view: View) {
        view.context.startActivity(Henson.with(view.context)
                .gotoEditItemActivity()
                .itemId(item.get().id!!)
                .build())
    }
}