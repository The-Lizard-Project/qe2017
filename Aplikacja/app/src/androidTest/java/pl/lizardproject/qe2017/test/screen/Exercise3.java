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
import pl.lizardproject.qe2017.pageobject.RegisterPageObject;
import pl.lizardproject.qe2017.register.RegisterActivity;

/*
 * Register screen test
 */
@RunWith(AndroidJUnit4.class)
public class Exercise3 {

    @Rule public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    private DatabaseFacade databaseFacade;

    @Before
    public void setUp() {
        databaseFacade = ((MyApplication) activityTestRule.getActivity().getApplicationContext()).getDatabaseFacade();
    }

    @After
    public void tearDown() {
        databaseFacade.drop();
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
}