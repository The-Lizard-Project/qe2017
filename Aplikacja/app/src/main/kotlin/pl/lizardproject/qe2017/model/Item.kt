package pl.lizardproject.qe2017.model

class Item(val id: Int?, val name: String, val category: Category, val priority: Priority, val isChecked: Boolean = false) {

    fun checkItem(check: Boolean) = Item(id, name, category, priority, check)
}