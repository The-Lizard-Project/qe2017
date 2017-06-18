package pl.lizardproject.qe2017.edititem

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.f2prateek.dart.Dart
import com.f2prateek.dart.InjectExtra

import pl.lizardproject.qe2017.MyApplication
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.databinding.ActivityEditItemBinding
import pl.lizardproject.qe2017.messages.Messenger
import pl.lizardproject.qe2017.navigation.AppNavigator

class EditItemActivity : AppCompatActivity() {

    @InjectExtra @JvmField var itemId: Int? = null

    private val myApplication: MyApplication by lazy { application as MyApplication }
    private val viewModel by lazy { EditItemViewModel(itemId, myApplication.databaseFacade, myApplication.userSession, AppNavigator(this), Messenger()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dart.inject(this)

        (DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_edit_item) as ActivityEditItemBinding).viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}