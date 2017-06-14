package pl.lizardproject.qe2017.itemlist

import android.databinding.ObservableField
import android.view.View
import android.widget.CompoundButton
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toDbModel
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.navigation.AppNavigator

class ItemViewModel(item: Item, private val databaseFacade: DatabaseFacade, private val appNavigator: AppNavigator) {

    val item = ObservableField(item)

    fun onCheckChangedCommand(ignored: CompoundButton, isChecked: Boolean) {
        if (item.get().isChecked != isChecked) {
            databaseFacade.saveItem(item.get().checkItem(isChecked).toDbModel())
        }
    }

    fun onDeleteClickCommand(ignored: View) {
        databaseFacade.deleteItem(item.get().toDbModel())
    }

    fun onClickCommand(ignored: View) {
        appNavigator.openEditItemActivity(item.get().id)
    }
}