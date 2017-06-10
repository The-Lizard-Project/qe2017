package pl.lizardproject.qe2017.edititem

import android.app.Activity
import android.databinding.ObservableField
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toAppModel
import pl.lizardproject.qe2017.database.converter.toDbModel
import pl.lizardproject.qe2017.model.Category
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.model.Priority

class EditItemViewModel(val itemId: Int?, private val activity: Activity, private val databaseFacade: DatabaseFacade) {

    val newItemName = ObservableField("")
    val newItemCategoryPosition = ObservableField(Category.FRUITS.ordinal)
    val newItemPriorityPosition = ObservableField(Priority.NORMAL.ordinal)

    private var subscription = databaseFacade.loadItem(itemId)
            .map { it.toAppModel() }
            .subscribe({
                newItemName.set(it.name)
                newItemCategoryPosition.set(it.category.ordinal)
                newItemPriorityPosition.set(it.priority.ordinal)
            }, { /*new item*/ })

    val categories = Category.values().map { it.toString().toLowerCase() }
    val priorities = Priority.values().map { it.toString().toLowerCase() }

    fun saveItemCommand(view: View) {
        if (!TextUtils.isEmpty(newItemName.get())) {
            val dbItem = Item(itemId, newItemName.get(), Category.values()[newItemCategoryPosition.get()], Priority.values()[newItemPriorityPosition.get()]).toDbModel()
            databaseFacade.saveItem(dbItem)
            activity.finish()
        } else {
            Snackbar.make(view, activity.getString(R.string.editItemError), Snackbar.LENGTH_SHORT).show()
        }
    }

    fun dispose() {
        subscription.unsubscribe()
    }
}