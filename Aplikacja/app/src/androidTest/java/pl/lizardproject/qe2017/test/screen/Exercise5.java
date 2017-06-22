package pl.lizardproject.qe2017.test.screen;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.lizardproject.qe2017.MyApplication;
import pl.lizardproject.qe2017.database.DatabaseFacade;
import pl.lizardproject.qe2017.database.model.DbUserEntity;
import pl.lizardproject.qe2017.edititem.EditItemActivity;
import pl.lizardproject.qe2017.edititem.Henson;
import pl.lizardproject.qe2017.session.UserSession;
import pl.lizardproject.qe2017.util.TestDataHelper;

/*
 * Edit item screen test
 */
@RunWith(AndroidJUnit4.class)
public class Exercise5 {

    @Rule public ActivityTestRule<EditItemActivity> activityTestRule = new ActivityTestRule<>(EditItemActivity.class, false, false);

    private DbUserEntity dbUser;
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        DatabaseFacade databaseFacade = ((MyApplication) InstrumentationRegistry.getTargetContext().getApplicationContext()).getDatabaseFacade();
        UserSession userSession = ((MyApplication) InstrumentationRegistry.getTargetContext().getApplicationContext()).getUserSession();
        testDataHelper = new TestDataHelper(databaseFacade);

        dbUser = testDataHelper.loginUser("user", "pass", userSession);
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
        activityTestRule.launchActivity(null);

    }

    /* TODO TASK 2
     *
     * 1. Add item to database
     * 2. Validate if the screen is opened with chosen item
     *
    */
    @Test
    public void validateScreenWithItem() {
        // use getActivityIntent method

    }

    /* TODO TASK 3
     *
     * 1. Add the item
     * 2. Validate if the item is added
     *
    */
    @Test
    public void addItem() {

    }

    /* TODO TASK 4
    *
    * 1. Add item to database
    * 2. Update the item
    * 3. Validate if the item is updated
    *
   */
    @Test
    public void editItem() {

    }

    /* TODO TASK 5
    *
    * 1. Add checked item to database
    * 2. Update the item
    * 3. Validate if the item is updated and not checked
    *
   */
    @Test
    public void editCheckedItem() {

    }

    private Intent getActivityIntent(int itemId) {
        return Henson.with(InstrumentationRegistry.getTargetContext())
                .gotoEditItemActivity()
                .itemId(itemId)
                .build();
    }
}