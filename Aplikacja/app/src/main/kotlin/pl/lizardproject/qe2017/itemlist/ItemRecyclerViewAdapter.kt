package pl.lizardproject.qe2017.itemlist

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.lizardproject.qe2017.MyApplication
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.databinding.ItemItemListBinding
import pl.lizardproject.qe2017.messages.Messenger
import pl.lizardproject.qe2017.model.Item
import pl.lizardproject.qe2017.navigation.AppNavigator

class ItemRecyclerViewAdapter(private val list: ObservableArrayList<Item>, private val messenger: Messenger, context: Context) : RecyclerView.Adapter<ItemRecyclerViewAdapter.BindingViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private val databaseFacade = (context.applicationContext as MyApplication).databaseFacade
    private val appNavigator = AppNavigator(context as Activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BindingViewHolder(layoutInflater.inflate(R.layout.item_item_list, parent, false))

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.binder.viewModel = ItemViewModel(list[position], databaseFacade, appNavigator, messenger)
        holder.binder.executePendingBindings()
    }

    override fun getItemCount() = list.count()

    class BindingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binder: ItemItemListBinding = DataBindingUtil.bind(view)
    }
}