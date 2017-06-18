package pl.lizardproject.qe2017.edititem

import android.databinding.ObservableField
import android.view.View
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.database.DatabaseFacade
import pl.lizardproject.qe2017.database.converter.toAppModel
import pl.lizardproject.qe2017.database.converter.toDbModel
import pl.lizardproject.qe2017.messages.Messenger
import pl.lizardproject.qe2017.model.Category
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.model.Priority
import pl.lizardproject.qe2017.navigation.AppNavigator
import pl.lizardproject.qe2017.session.UserSession

class EditItemViewModel(private val itemId: Int?, private val databaseFacade: DatabaseFacade, private val userSession: UserSession, private val appNavigator: AppNavigator, private val messenger: Messenger) {

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
        if (newItemName.get().isNotBlank()) {
            val dbItem = Item(itemId, newItemName.get(), Category.values()[newItemCategoryPosition.get()], Priority.values()[newItemPriorityPosition.get()], userSession.user!!).toDbModel()
            databaseFacade.saveItem(dbItem)
                    .subscribe(
                            { appNavigator.closeActivity() },
                            { messenger.showMessage(view, it.message!!) })
        } else {
            messenger.showMessage(view, R.string.editItemErrorEmptyName)
        }
    }

    fun dispose() {
        subscription.unsubscribe()
    }
}