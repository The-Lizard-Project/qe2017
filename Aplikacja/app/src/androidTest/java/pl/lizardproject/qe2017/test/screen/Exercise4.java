package pl.lizardproject.qe2017.test.screen;

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
import pl.lizardproject.qe2017.itemlist.ItemListActivity;
import pl.lizardproject.qe2017.model.Category;
import pl.lizardproject.qe2017.model.Priority;
import pl.lizardproject.qe2017.pageobject.ItemListPageObject;
import pl.lizardproject.qe2017.session.UserSession;
import pl.lizardproject.qe2017.util.TestDataHelper;

/*
 * Item list screen test
 */
@RunWith(AndroidJUnit4.class)
public class Exercise4 {

    @Rule public ActivityTestRule<ItemListActivity> activityTestRule = new ActivityTestRule<>(ItemListActivity.class, false, false);

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

        new ItemListPageObject()
                .validate();
    }

    /* TODO TASK 2
     *
     * 1. Click on add button
     * 2. Validate if the screen is opened
     *
    */
    @Test
    public void openAddItemScreen() {
        activityTestRule.launchActivity(null);

        new ItemListPageObject()
                .openAddItemScreen()
                .validate("", Category.FRUITS, Priority.NORMAL);
    }

    /* TODO TASK 3
    *
    * 1. Add item to database - use addItemToDatabase method from PageObject
    * 1. Click on item
    * 2. Validate if the screen is opened
    *
   */
    @Test
    public void openEditItemScreen() {
        String itemName = "new item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        boolean isChecked = false;
        testDataHelper.addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser);

        activityTestRule.launchActivity(null);

        new ItemListPageObject()
                .clickOnItem(itemName)
                .validate(itemName, itemCategory, itemPriority);
    }

    /* TODO TASK 4
     *
     * 1. Add item to database
     * 2. Remove item
     * 3. Validate if the item is removed
     *
    */
    @Test
    public void removeItem() {
        String itemName = "new item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        boolean isChecked = false;
        testDataHelper.addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser);

        activityTestRule.launchActivity(null);

        new ItemListPageObject()
                .removeItem(itemName)
                .validateItemNotExists(itemName, itemCategory, itemPriority, isChecked);
    }

    /* TODO TASK 5
     *
     * 1. Add item to database
     * 2. Check the item
     * 3. Validate if the item is checked
     *
    */
    @Test
    public void checkItem() {
        String itemName = "new item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        boolean isChecked = false;
        testDataHelper.addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser);

        activityTestRule.launchActivity(null);

        new ItemListPageObject()
                .checkItem(itemName)
                .validateItemExists(itemName, itemCategory, itemPriority, !isChecked);
    }
}