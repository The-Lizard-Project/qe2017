package pl.lizardproject.qe2017.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pl.lizardproject.qe2017.MyApplication
import pl.lizardproject.qe2017.R
import pl.lizardproject.qe2017.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val application: MyApplication by lazy { getApplication() as MyApplication }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).viewModel = LoginViewModel(application.databaseFacade, application.userSession)
    }
}