package pl.lizardproject.qe2017.databinding

import android.databinding.BindingAdapter
import android.databinding.InverseBindingMethod
import android.databinding.InverseBindingMethods
import android.databinding.ObservableArrayList
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Spinner
import pl.lizardproject.qe2017.itemlist.ItemRecyclerViewAdapter
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.view.itemdecoration.DividerItemDecoration

@BindingAdapter("items")
fun bindRecyclerViewItems(view: RecyclerView, list: ObservableArrayList<Item>) {
    if (view.adapter == null) {
        view.layoutManager = LinearLayoutManager(view.context)
        view.adapter = ItemRecyclerViewAdapter(list, view.context)
        view.addItemDecoration(DividerItemDecoration(view.context, (view.layoutManager as LinearLayoutManager).orientation))
    } else {
        view.adapter.notifyDataSetChanged()
    }
}

@InverseBindingMethods(InverseBindingMethod(type = Spinner::class, attribute = "android:selectedItemPosition"))
class SpinnerBindingAdapters {}