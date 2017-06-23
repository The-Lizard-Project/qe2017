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
import pl.lizardproject.qe2017.util.TestDataHelper;

/*
 * Login screen test
 */
@RunWith(AndroidJUnit4.class)
public class Exercise2 {

    @Rule public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        DatabaseFacade databaseFacade = ((MyApplication) activityTestRule.getActivity().getApplication()).getDatabaseFacade();
        testDataHelper = new TestDataHelper(databaseFacade);
    }

    @After
    public void tearDown() {
        testDataHelper.dropDatabase();
    }

    /* TODO TASK 1
     *
     * 1. Add code inside LoginPageObject
     * 2. Validate if the screen is opened
     *
    */
    @Test
    public void openLoginScreen() {
        new LoginPageObject();
    }

    /* TODO TASK 2
     *
     * 1. Open register screen
     * 2. Add code inside RegisterPageObject
     * 3. Validate if the screen is opened
     *
    */
    @Test
    public void openRegisterScreen() {

    }

    /* TODO TASK 3
     *
     * 1. Add user to database - use addUserToDatabase method from testDataHelper
     * 2. Login as added user
     * 3. Add code inside ItemListPageObject
     * 4. Validate if the screen is opened
     *
    */
    @Test
    public void login() {

    }

    ////////////////// For volunteers //////////////////

    /* TODO TASK 4
     *
     * 1. Add code inside LoginPageObject
     * 2. Try to login
     * 3. Validate if the error is displayed
     *
    */
    @Test
    public void loginError() {

    }

    /* TODO TASK 5
     *
     * 1. Add user to database
     * 2. Try to login with wrong password
     * 3. Validate if the error is displayed
     *
    */
    @Test
    public void loginWithWrongPasswordError() {
        String username = "user";
        String password = "password";

        testDataHelper.addUserToDatabase(username, password);

        new LoginPageObject()
                .loginWithError(username, "wrong password")
                .validateError();
    }
}