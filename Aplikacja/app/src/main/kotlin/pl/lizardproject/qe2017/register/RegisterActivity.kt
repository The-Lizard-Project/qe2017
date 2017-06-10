package pl.lizardproject.qe2017.register

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pl.lizardproject.qe2017.MyApplication
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val application: MyApplication by lazy { getApplication() as MyApplication }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register).viewModel = RegisterViewModel(application.databaseFacade, application.userSession)
    }
}