package pl.lizardproject.qe2017.navigation

import android.app.Activity
import android.content.Intent
import pl.lizardproject.qe2017.edititem.Henson
import pl.lizardproject.qe2017.itemlist.ItemListActivity
import pl.lizardproject.qe2017.login.LoginActivity
import pl.lizardproject.qe2017.register.RegisterActivity

class AppNavigator(private val activity: Activity) {
    companion object {
        private const val NEW_STACK_INTENT = Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK
    }

    fun openLoginActivity() {
        val intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(NEW_STACK_INTENT)
        activity.startActivity(intent)
    }

    fun openRegisterActivity() {
        activity.startActivity(Intent(activity, RegisterActivity::class.java))
    }

    fun openItemListActivity() {
        val intent = Intent(activity, ItemListActivity::class.java)
        intent.addFlags(NEW_STACK_INTENT)
        activity.startActivity(intent)
    }

    fun openEditItemActivity(itemId: Int? = null) {
        activity.startActivity(Henson.with(activity)
                .gotoEditItemActivity()
                .itemId(itemId)
                .build())
    }
}