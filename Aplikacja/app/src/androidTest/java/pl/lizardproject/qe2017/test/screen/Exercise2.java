package pl.lizardproject.qe2017.test.screen;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.lizardproject.qe2017.MyApplication;
import pl.lizardproject.qe2017.database.DatabaseFacade;
import pl.lizardproject.qe2017.login.LoginActivity;
import pl.lizardproject.qe2017.pageobject.LoginPageObject;

/*
 * Login screen test
 */
@RunWith(AndroidJUnit4.class)
public class Exercise2 {

    @Rule public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private DatabaseFacade databaseFacade;

    @Before
    public void setUp() {
        databaseFacade = ((MyApplication) activityTestRule.getActivity().getApplication()).getDatabaseFacade();
    }

    @After
    public void tearDown() {
        databaseFacade.drop();
    }

    /* TODO TASK 1
     *
     * 1. Add code inside LoginPageObject
     * 2. Validate if the screen is opened
     *
    */
    @Test
    public void openLoginScreen() {
        new LoginPageObject()
                .validate();
    }

    /* TODO TASK 2
     *
     * 1. Open register screen
     * 2. Add code inside RegisterPageObject
     * 3, Validate if the screen is opened
     *
    */
    @Test
    public void openRegisterScreen() {
        new LoginPageObject()
                .openRegisterScreen()
                .validate();
    }

    /* TODO TASK 3
     *
     * 1. Add user to database - use addUserToDatabase method from PageObject
     * 2, Login as added user
     * 3. Add code inside ItemListPageObject
     * 4. Validate if the screen is opened
     *
    */
    @Test
    public void login() {
        String username = "user";
        String password = "password";

        new LoginPageObject()
                .addUserToDatabase(username, password, databaseFacade)
                .login(username, password)
                .validate();
    }
}