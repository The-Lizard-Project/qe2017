package pl.lizardproject.qe2017.messages

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

class Messenger {
    fun showMessage(view: View, @StringRes stringId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, view.context.getString(stringId), duration).show()
    }
}