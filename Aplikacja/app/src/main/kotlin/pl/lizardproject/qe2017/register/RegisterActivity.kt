package pl.lizardproject.qe2017.register

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pl.lizardproject.qe2017.MyApplication
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.databinding.ActivityRegisterBinding
import pl.lizardproject.qe2017.navigation.AppNavigator

class RegisterActivity : AppCompatActivity() {

    private val myApplication: MyApplication by lazy { application as MyApplication }
    private val viewModel by lazy { RegisterViewModel(myApplication.databaseFacade, myApplication.userSession, AppNavigator(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register).viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}