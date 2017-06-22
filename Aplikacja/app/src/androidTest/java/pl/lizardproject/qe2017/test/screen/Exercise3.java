package pl.lizardproject.qe2017.test.screen;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.lizardproject.qe2017.MyApplication;
import pl.lizardproject.qe2017.R;
import pl.lizardproject.qe2017.database.DatabaseFacade;
import pl.lizardproject.qe2017.pageobject.RegisterPageObject;
import pl.lizardproject.qe2017.register.RegisterActivity;
import pl.lizardproject.qe2017.util.TestDataHelper;

/*
 * Register screen test
 */
@RunWith(AndroidJUnit4.class)
public class Exercise3 {

    @Rule public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);

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
     * 1. Validate if the screen is opened
     *
    */
    @Test
    public void validateScreen() {
        new RegisterPageObject()
                .validate();
    }

    /* TODO TASK 2
     *
     * 1. Register user
     * 2. Validate if the screen is opened
     *
    */
    @Test
    public void createUser() {
        String username = "user";
        String password = "password";

        new RegisterPageObject()
                .createUser(username, password)
                .validate();
    }

    ////////////////// For volunteers //////////////////

    /* TODO TASK 3
     *
     * 1. Add code inside RegisterPageObject
     * 2, Try to register with empty edit texts
     * 3. Validate if the error is displayed
     *
    */
    @Test
    public void registerError() {
        String username = "";
        String password = "";

        new RegisterPageObject()
                .createUserWithError(username, password)
                .validateError(R.string.registerError);
    }

    /* TODO TASK 4
     *
     * 1. Add user to database
     * 2, Try to register with existing user
     * 3. Validate if the error is displayed
     *
    */
    @Test
    public void registerWithExistingUserError() {
        String username = "user";
        String password = "password";

        testDataHelper.addUserToDatabase(username, password);

        new RegisterPageObject()
                .createUserWithError(username, password)
                .validateError(R.string.registerErrorUserExists);
    }
}